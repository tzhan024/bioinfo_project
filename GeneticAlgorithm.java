<<<<<<< Updated upstream
=======
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
>>>>>>> Stashed changes
import java.util.Random;
import java.util.Scanner;

public class GeneticAlgorithm {

	Population population = new Population();
	int[] top10;
	int[] least10;
	int generationCount = 0;

	public static void main(String[] args) {
		GeneticAlgorithm ga = new GeneticAlgorithm();
		Individual bestIndividual = null;
		int bestFittness = 0;

		//data was taken from: http://lbgi.fr/wscoperr?Balibase&FileMoi&tfas&BB11001
		String[] BB11001 =
				{
						"GKGDPKKPRGKMSSYAFFVQTSREEHKKKHPDASVNFSEFSKKCSERWKTMSAKEKGKFEDMAKADKARYEREMKTYIPPKGE",
						"MQDRVKRPMNAFIVWSRDQRRKMALENPRMRNSEISKQLGYQWKMLTEAEKWPFFQEAQKLQAMHREKYPNYKYRPRRKAKMLPK",
						"MKKLKKHPDFPKKPLTPYFRFFMEKRAKYAKLHPEMSNLDLTKILSKKYKELPEKKKMKYIQDFQREKQEFERNLARFREDHPDLIQNAKK",
						"MHIKKPLNAFMLYMKEMRANVVAESTLKESAAINQILGRRWHALSREEQAKYYELARKERQLHMQLYPGWSARDNYGKKKKRKREK"
				};
		String[] BB11013 =
				{
						"MEVKKTSWTEEEDRILYQAHKRLGNRWAEIAKLLPGRTDNAIKNHWNSTMRRKV",
						"SHPTYSEMIAAAIRAEKSRGGSSRQSIQKYIKSHYKVGHNADLQIKLSIRRLLAAGVLKQTKGVGASGSFRLAK",
						"PRGSALSDTERAQLDVMKLLNVSLHEMSRKISRSRHCIRVYLKDPVSYGTS",
						"MRSSAKQEELVKAFKALLKEEKFSSQGEIVAALQEQGFDNINQSKVSRMLTKFGAVRTRNAKMEMVYCLPAELGVPTT",
						"SAAMAEQRHQEWLRFVDLLKNAYQNDLHLPLLNLMLTPDEREALGTRVRIIEELLRGEMSQRELKNELGAGIATITRGSNSLKAAPVELRQWLEEVLLKSD"

				};
		String[] BB11008 =
				{
						"LQDAEWYWGDISREEVNEKLRDTADGTFLVRDASTKMHGDYTLTLRKGGN" +
								"NKLIKIFHRDGKYGFSDPLTFNSVVELINHYRNESLAQYNPKLDVKLLYP" +
								"VSKY",

						"GSPASGTSLSAAIHRTQLWFHGRISREESQRLIGQQGLVDGLFLVRESQR" +
								"NPQGFVLSLCHLQKVKHYLILPSEEEGRLYFSMDDGQTRFTDLLQLVEFH" +
								"QLNRGILPCLLRHCCTRVAL",

						"SSPQPILDTIYKLLSEQEQTLVQMIHEQSLLLNRLPPTLDENSLAPLKSL" +
								"SQKQITLSGQMNTEMSALDATKKGMILEPTDLAKLFALKQDLQIQFKQLS" +
								"LLHNEIQSILNPQHSAPKPNVALVLKSQPFPVVISKGKQLGENQLVVLVL" +
								"TGARSNFHINGPVKATMICDSHPPTTPLEMDSQPIYPATLTAHFPLKFLA" +
								"GTRKCSVNLKFGVNIRDLDNVTTTVESDASNPFVVITNECQWEGSAGVLL" +
								"KKDAFDGQLEITWAQFINTLQRHFLIATKQDPVRPKRPLSSYDLKYIQTH" +
								"FFGNRSIIHQQDFDKFWVWFGKSMQTLRYQRHISTLWQEGIIYGYMGRQE" +
								"VNDALQNQDPGTFIIRFSERNPGQFGIAYIGVEMPARIKHYLVQPNDTAA" +
								"AKKTFPDFLSEHSQFVNLLQWTKDTNGAPRFLKLHKDTALGSFAPKRTAP" +
								"VPVGGX",

						"LDKQKELDSKVRNVKDKVMCIEHEIKSLEDLQDEYDFKCKTLQNREHLLL" +
								"KKMYLMLDNKRKEVVHKIIELLNVTELTQNALINDELVEWKRRQQSACIG" +
								"GPPNACLDQLQNWFTIVAESLQQVRQQLKKLEELEQKYTYEHDPITKNKQ" +
								"VLWDRTFSLFQQLIQSSFVVERQPCMPTHPQRPLVLKTGVQFTVKLRLLV" +
								"KLQELNYNLKVKVLFDKDVNERNTVKGFRKFNILGTHTKVMNMEESTNGS" +
								"LAAEFRHLQLKEQKNAGTRTNEGPLIVTEELHSLSFETQLCQPGLVIDLE" +
								"TTSLPVVVISNVSQLPSGWASILWYNMLVAEPRNLSFFLTPPCARWAQLS" +
								"EVLSWQFSSVTKRGLNVDQLNMLGEKLLGPNASPDGLIPWTRFCKENIND" +
								"KNFPFWLWIESILELIKKHLLPLWNDGCIMGFISKERERALLKDQQPGTF" +
								"LLRFSESSREGAITFTWVERSQNGGEPDFHAVEPYTKKELSAVTFPDIIR" +
								"NYKVMAAENIPENPLKYLYPNIDKDHAFGKYYSRGXIKTE",
				};


		String[] BB11001Result =
				{
						"___GKGDPKKPRGKMSSYAFFVQTSREEHKKKHPDASVNFSEFSKKCSERWKTMSAKEKGKFEDMAKADKARYEREMKTYIPPKGE__________",
						"______MQDRVKRPMNAFIVWSRDQRRKMALENP__RMRNSEISKQLGYQWKMLTEAEKWPFFQEAQKLQAMHREKYPNYKYRPRRKAKMLPK___",
						"MKKLKKHPDFPKKPLTPYFRFFMEKRAKYAKLHP__EMSNLDLTKILSKKYKELPEKKKMKYIQDFQREKQEFERNLARFREDHPDLIQNAKK___",
						"________MHIKKPLNAFMLYMKEMRANVVAEST__LKESAAINQILGRRWHALSREEQAKYYELARKERQLHMQLYPGWSARDNYGKKKKRKREK",
				};
		String[] BB11013Result =
				{
						"...................................MEVKKTSWTEEEDRILYQAHKRLG." +
								"  NRWAEIAKLLP.....GRTDNAIKNHWNSTMRRKV....................",

						"......................................SHPTYSEMIAAAIRAEKSRGG." +
								"  SSRQSIQKYIKSHYKVGHNADLQIKLSIRRLLAAGVLKQTKGVGASGSFRLAK..",

						"......................................PRGSALSDTERAQLDVMKLLN." +
								"  VSLHEMSRKIS......RSRHCIRVYLKDPVSYGTS...................",

						".....................................MRSSAKQEELVKAFKALLKEEKF" +
								"  SSQGEIVAALQEQGFDNINQSKVSRMLTKFGAVRTRNAKMEMVYCLPAELGVPTT",

						"SAAMAEQRHQEWLRFVDLLKNAYQNDLHLPLLNLMLTPDEREALGTRVRIIEELLRGE.." +
								"  MSQRELKNELG......AGIATITRGSNSLKAAPVELRQWLEEVLLKSD......"
				};
		String[] BB11008Result =
				{
						"............................................................" +
								"  ............................................................" +
								"  ............................................................" +
								"  ............................................................" +
								"  ............................................................" +
								"  ............................................................" +
								"  ............................................................" +
								"  ..LQDAEWYWGDISREEVNEKLRDT..ADGTFLVRDASTKMHGDYTLTLRKGG.....NN" +
								"  KLIKIFHRD....................GKYGFSDP.....LTFNSVVELINHYRNESL" +
								"  AQYN.PKLDVKLLYPVSKY",

						"............................................................" +
								"  ............................................................" +
								"  ............................................................" +
								"  ............................................................" +
								"  ............................................................" +
								"  ............................................................" +
								"  .................................................GSPASGTSLSA" +
								"  AIHRTQLWFHGRISREESQRLIGQQGLVDGLFLVRESQRNP.QGFVLSLCHLQ.....KV" +
								"  KHYLILPSEE..................EGRLYFSMDDGQ..TRFTDLLQLVEFHQLNRG" +
								"  ....ILPCLLRHCCTRVAL",

						"............................................................" +
								"  ...........................SSPQPILDTIYKLLSEQEQTLVQMIHEQSLLLN" +
								"  RLPPTLDENSLAPLKSLSQKQITLSGQMNTEMSALDATKKGMILEPTDLAKLFALKQDLQ" +
								"  IQFKQLSLLHNEIQSILNPQHSAPKPNVALVLKSQPFPVVISKGKQLGENQLVVLVLTGA" +
								"  RSNFHINGPVKATMICDSHPPTTPLEMDSQPIYPATLTAHFPLKFLAGTRKCSVNLKFGV" +
								"  NIRDLDNVTTTVESDASNPFVVITNECQWEGSAGVLLKKDAFDGQLEITWAQFINTLQRH" +
								"  FLIATKQDPVRPKRPLSSYDLKYIQTHFFGNRSIIHQQDFDKFWVWFGKSMQTLRYQRHI" +
								"  STLWQEGIIYGYMGRQEVNDALQNQ..DPGTFIIRFSERNP.GQFGIAYIGVEMPA..RI" +
								"  KHYLVQPNDTAAAKKTFPDFLSEHSQFVNLLQWTKDT..NGAPRFLKLHKDTALGSFAPK" +
								"  RTA...PVPVGGX......",

						"LDKQKELDSKVRNVKDKVMCIEHEIKSLEDLQDEYDFKCKTLQNREHLLLKKMYLMLDNK" +
								"  RKEVVHKIIELLNVTELTQNALINDELVEWKRRQQSACIGGPPNACLDQLQNWFTIVAES" +
								"  LQQVRQQLKKLEELEQKYTYEHDPITKNKQVLWDRTFSLFQQLIQSSFVVERQPCMPTHP" +
								"  QRPLVLKTGVQFTVKLRLLVKLQELNYNLKVKVLFDKDVNERNTVKGFRKFNILGTHTKV" +
								"  MNMEESTNGSLAAEFRHLQLKEQKNAGTRTNEGPLIVTEELHSLSFETQLCQPGLVIDLE" +
								"  TTSLPVVVISNVSQLPSGWASILWYNMLVAEPRNLSFFLTPPCARWAQLSEVLSWQFSSV" +
								"  TKRGLNVDQLNMLGEKLLGPNASPDGLIPWTRFCKENINDKNFPFWLWIESILELIKKHL" +
								"  LPLWNDGCIMGFISKERERALLKDQ..QPGTFLLRFSESSREGAITFTWVERSQNGGEPD" +
								"  FHAVEPYTKKELSAVTFPDIIRN..YKVMAAENIPEN..PLKYLYPNIDKDHAFGKYYSR" +
								"  G.......XIKTE......",

				};

		//Initialize population
		System.out.println("Please select dataset: ");
		System.out.println("1 -> BB11001");
		System.out.println("2 -> BB11003");
		System.out.println("3 -> BB11008");
		Scanner in = new Scanner(System.in);
		String choose = in.nextLine();
		if(choose.equals("1") )
		{
			Individual callfunc = new Individual(BB11001);
			String[] firstalign = new String[BB11001.length];
			for(int i = 0; i < BB11001.length-1; i++)
	        {
				firstalign[i]=callfunc.algin(BB11001[i],BB11001[(i+1)])[0];
				if(i==BB11001.length-2)
				{
					firstalign[i+1]=callfunc.algin(BB11001[i],BB11001[(i+1)%BB11001.length])[1];
				}
	        }
			ga.population.initializePopulation(200, firstalign);
		}
			
		else if(choose.equals("2"))
		{
			Individual callfunc = new Individual(BB11013);
			String[] firstalign = new String[BB11013.length];
			for(int i = 0; i < BB11013.length-1; i++)
	        {
				firstalign[i]=callfunc.algin(BB11013[i],BB11013[(i+1)])[0];
				if(i==BB11013.length-2)
				{
					firstalign[i+1]=callfunc.algin(BB11013[i],BB11013[(i+1)%BB11013.length])[1];
				}
	        }
			ga.population.initializePopulation(200, BB11013);
		}
			//ga.population.initializePopulation(200, BB11013);
		else if(choose.equals("3"))
		{
			Individual callfunc = new Individual(BB11008);
			String[] firstalign = new String[BB11008.length];
			for(int i = 0; i < BB11008.length-1; i++)
	        {
				firstalign[i]=callfunc.algin(BB11008[i],BB11008[(i+1)])[0];
				if(i==BB11008.length-2)
				{
					firstalign[i+1]=callfunc.algin(BB11008[i],BB11013[(i+1)%BB11008.length])[1];
				}
	        }
			ga.population.initializePopulation(200, BB11008);
		}
			//ga.population.initializePopulation(200, BB11008);
		else
		{
<<<<<<< Updated upstream
			System.out.println("invalid input");
			return;
=======
			String[] customSeq = FileReader.tfaReader( choose + ".tfa");
			for(int i = 0; i < customSeq.length; i++)
			{
				System.out.println("custom: " + customSeq[i]);
			}
			Individual callfunc = new Individual(customSeq);
			String[] firstalign = new String[customSeq.length];
			for(int i = 0; i < customSeq.length-1; i++)
			{
				firstalign[i]=callfunc.algin(customSeq[i],customSeq[(i+1)])[0];
				if(i == customSeq.length-2)
				{
					firstalign[i+1]=callfunc.algin(customSeq[i],customSeq[(i+1)%customSeq.length])[1];
				}
			}
			ga.population.initializePopulation(200, firstalign); //can switch back to orgin sequence
>>>>>>> Stashed changes
		}

		//Calculate fitness of each individual
		ga.population.calculateFitness();

		bestIndividual = ga.population.getFittest();
		bestFittness = ga.population.highestFittness;
		System.out.println("Generation: " + ga.generationCount + " Fittest: " + bestFittness);

		//While population gets an individual with maximum fitness
		int count = 0;
		while (count < 500) {
			ga.generationCount++;

			//Do selection
			ga.selection();

			//Do crossover
			ga.crossover();

			//Do mutation under a random probability
			if (Math.random() < 0.5)
			{
				ga.mutation();
			}

			//Calculate new fitness value
			ga.population.calculateFitness();


			if(bestFittness < ga.population.highestFittness)
			{
				bestIndividual = ga.population.getFittest();
				bestFittness = ga.population.highestFittness;

			}
			else
				count++;

			System.out.println("Generation: " + ga.generationCount + " Fittest: " + bestFittness);
		}

		System.out.println("\nSolution found in generation " + ga.generationCount);
		System.out.println("\nHere are the sequences: ");
		for(int i = 0; i < bestIndividual.sequence.length; i++)
		{
			System.out.println(bestIndividual.sequence[i]);
		}
		
		int gaResult = ga.population.getFittest().fitness;
		System.out.println("\nThe best fitness for genetic algorithm is: " + gaResult);

		Individual result;
		if(choose.equals("1") )
		{
			result = new Individual(BB11001Result);
		}
		else if(choose.equals("2"))
		{
			result = new Individual(BB11013Result);
		}
		else
		{
<<<<<<< Updated upstream
			result = new Individual(BB11008Result);
=======
			String[] customResult = FileReader.xmlReader(choose + ".xml");
			result = new Individual(customResult);
>>>>>>> Stashed changes
		}

		result.calcFitness();
		System.out.println("The fitness for the comparing algorithm is: " + result.fitness);
		System.out.println("The accuracy is: " + (double)gaResult / result.fitness);
	}

	//Selection
	void selection() {

		//Select the most fittest individual

		top10 = population.getTop10();
		least10 = population.getLeast10();
	}

	//Crossover
	void crossover() {
		Random rn = new Random();

		//Select a random crossover point
		int crossOverPoint = rn.nextInt(population.individuals[0].sequenceLength);


		//Swap values among parents
		for(int i = 0; i < top10.length; i++)
		{
			for (int j = 0; j < population.individuals[top10[i]].sequence.length; j++) {
//				System.out.println(crossOverPoint + " " + population.individuals[0].sequenceLength);
//				System.out.println("before crossover " + fittest.sequence[i]);
//				System.out.println("before crossover " + secondFittest.sequence[i]);

				String fittest1 = population.individuals[top10[i]].sequence[j].substring(0, crossOverPoint);
				String fittest2 = population.individuals[top10[(i + 1) % top10.length]].sequence[j].substring(crossOverPoint);
				String secondFittest1 = population.individuals[top10[i]].sequence[j].substring(0, crossOverPoint);
				String secondFittest2 = population.individuals[top10[(i + 1) % top10.length]].sequence[j].substring(crossOverPoint);

				population.individuals[least10[i]].sequence[j] = fittest1 + secondFittest2;
				population.individuals[least10[(i + 1) % least10.length]].sequence[j] = secondFittest1 + fittest2;

//				System.out.println("after  crossover " + fittest.sequence[i]);
//				System.out.println("after  crossover " + secondFittest.sequence[i]);
			}
		}


	}

	//Mutation
	void mutation()
	{
		Random rn = new Random();
		//Select a random mutation point
		int mutationPoint = rn.nextInt(population.individuals[0].sequenceLength);
		
		int individualIndex = rn.nextInt(population.individuals.length);
		int proteinIndex = rn.nextInt(population.individuals[0].sequence.length);
		
		List<Integer> list=new ArrayList<Integer>();
		for(int i = 0;i<population.individuals[individualIndex].sequence[proteinIndex].length();i++)
		{
			if(population.individuals[individualIndex].sequence[proteinIndex].charAt(i) == '_')
			{
				
				list.add(i);
			}
		}
		//System.out.println("list:"+list.size());
		int deletePoint = rn.nextInt(list.size());
		//System.out.println("list:"+list);
		if(population.individuals[individualIndex].sequence[proteinIndex].charAt(mutationPoint) != '_')
		{
			population.individuals[individualIndex].sequence[proteinIndex] =
					population.individuals[individualIndex].sequence[proteinIndex].substring(0, list.get(deletePoint)) +
					population.individuals[individualIndex].sequence[proteinIndex].substring(list.get(deletePoint)+1);
					
					//population.individuals[individualIndex].sequence[proteinIndex].substring(mutationPoint);
			population.individuals[individualIndex].sequence[proteinIndex] =
					population.individuals[individualIndex].sequence[proteinIndex].substring(0, mutationPoint) +
<<<<<<< Updated upstream
							"_" +
							population.individuals[individualIndex].sequence[proteinIndex].substring(mutationPoint + 1);
=======
					"_"+
					population.individuals[individualIndex].sequence[proteinIndex].substring(mutationPoint);
>>>>>>> Stashed changes
		}
		else
		{/*
			population.individuals[individualIndex].sequence[proteinIndex] =
			population.individuals[individualIndex].sequence[proteinIndex].substring(0, mutationPoint) +
			population.individuals[individualIndex].sequence[proteinIndex].substring(mutationPoint + 1) +
			"_";*/
		}
	}
}
