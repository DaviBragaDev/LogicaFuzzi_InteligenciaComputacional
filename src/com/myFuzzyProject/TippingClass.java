package com.myFuzzyProject;

import java.util.Scanner;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;

public class TippingClass {
	public static void main(String[] args) throws Exception {
		Scanner ler = new Scanner(System.in);
		float viscosidade, cor;
		
		String filename = "tipper.fcl"; //arquivo FCL
		FIS fis = FIS.load(filename, true); //carregamento

		if (fis == null) { //erro durante o carregamento do arquivo
			System.err.println("Can't load file: '" + filename + "'");
			System.exit(1);
		}

		// Get default function block
		FunctionBlock fb = fis.getFunctionBlock(null);

	
		
		// Defini��es de vari�veis de entrada FIS
		System.out.println("Como estava a viscosidade após o CMT(0-liquido, 10-Muito gelatinoso)? ");
		viscosidade = ler.nextFloat();
		System.out.println("Como esta a cor. Ela pode variar partindo do amarelo, o mais comum é um roxo claro mas pode ser que esteja muito forte o tom de roxo (0-Amarelo, 5-Roxo muito forte)? ");
		cor = ler.nextFloat();
		fb.setVariable("visco", viscosidade);  //8,5
		fb.setVariable("color", cor);  //7,5  tip=19.99999

		// Execu��o do sistema
		fb.evaluate();

		// Show output variable's chart
		fb.getVariable("mastite").defuzzify();
		fb.getVariable("ph").defuzzify();

		System.out.println("mastite: " + fb.getVariable("mastite").getValue());
		System.out.println("PH: " + fb.getVariable("ph").getValue());

		if(fb.getVariable("mastite").getValue() <= 10){
			System.out.println("Negativo para mastite");
		}else if(fb.getVariable("mastite").getValue() > 10 &&fb.getVariable("mastite").getValue() <= 30){
			System.out.println("Traços de mastite");
		}else if(fb.getVariable("mastite").getValue() > 30 &&fb.getVariable("mastite").getValue() <= 60){
			System.out.println("Mastite fraca");
		}
		else if(fb.getVariable("mastite").getValue() > 60 &&fb.getVariable("mastite").getValue() <= 80){
			System.out.println("Mastite Forte");
		}else if(fb.getVariable("mastite").getValue() > 80){
			System.out.println("Mastite muito forte");
		}
		if(fb.getVariable("ph").getValue() <= 1){
			System.out.println("Leite ácido no úbere é raro. Quando encontrado é indicativo de fermentação da Lactose por ação de bactérias.");
		}else if(fb.getVariable("ph").getValue() > 1 && fb.getVariable("ph").getValue() <4){
			System.out.println("Tudo normal com o PH");
		}else if(fb.getVariable("ph").getValue() > 4){
			System.out.println("Uma reação alcalina reflete depressão da atividade secretória. Isto pode ocorrer por causa de uma inflamação ou por uma glândula seca.");
		}


	}

}
