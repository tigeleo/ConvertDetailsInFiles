package primo_test.primo_test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;
import com.exlibris.jaguar.server.search.LQuery;;


class TestLQuery {

	
	@Test
	void test0() {
		String exp = "((((war) OR (WAR)) AND title:((\"peace\") OR (\"PEACE\")) NOT ((\"Black mamaba\") OR (\"BLACK MAMABA\"))) OR ( swstitle:((\"green*\") OR (\"GREEN*\")) NOT sub:((sharm) OR (SHARM)) AND usertag:((barmaglot vs alice ) OR (BARMAGLOT VS ALICE )) AND ((fuck vs tedy bear) OR (FUCK VS TEDY BEAR))) OR ((number) OR (NUMBER)))";
		System.out.println(exp);
		LQuery lq = new LQuery(exp);
		lq.print();
		lq.printTerms();
		lq.changeExpAndAddAsOr((s,l)->( (new String[] {"war","peace","green","sharm","barmaglot vs alice","fuck vs tedy bear","number"})[(new Random()).nextInt(7)]),"ENG");
		lq.print();	
		lq.printTerms();
		lq.changeExpAndAddAsOr((s,l)->("yyyyy"),"ENG");
		lq.print();
		lq.printTerms();
		lq.changeExpAndAddAsOr((s,l)->("zzzzz"),"ENG");
		lq.print();
		System.out.println("---------------------------------------------------------------");
		lq.printTerms();
		
		System.out.println("---------------------------------------------------------------");
		lq.getTermsOfPossitive().stream().forEach(e->System.out.println(e));
		
	}
	
	@Test
	void test01() {
		String exp = "((((war) NOT (WAR)) AND title:((\"peace\") AND (\"PEACE\")) NOT ((\"Black mamaba\") AND (\"BLACK MAMABA\"))) OR ( swstitle:((\"green*\") OR (\"GREEN*\")) NOT sub:((sharm) OR (SHARM)) AND usertag:((barmaglot vs alice ) NOT (BARMAGLOT VS ALICE )) AND ((fuck vs tedy bear) OR (FUCK VS TEDY BEAR))) OR ((number) OR (NUMBER)))";
		System.out.println(exp);
		LQuery lq = new LQuery(exp);
		lq.print();
		System.out.println("---------------------------------------------------------------");
		lq.printTerms();
		
		System.out.println("---------------------------------------------------------------");
		lq.getTermsOfPossitive().stream().forEach(e->System.out.println(e));
		lq.changeExpAndAddAsOr((s,l)->("xxxxx"),"ENG");
		lq.print();		

	
	
	}

	@Test
	void test02() {
		String exp = "check";
		System.out.println(exp);
		LQuery lq = new LQuery(exp);
		lq.print();
		lq.printTerms();
		lq.changeExpAndAddAsOr((s,l)->("xxxxx"),"ENG");
		lq.print();		
	}

	
	@Test
	void test2() {
		String exp = "(((war) AND title:(\"peace\")   NOT (\"Black mamaba\" )) OR ( swstitle:(\"green*\") NOT sub:(sharm) AND usertag:(barmaglot vs alice ) AND (fuck vs tedy bear)) OR (number))";
		System.out.println(exp);
		LQuery lq = new LQuery(exp);
		lq.print();
		lq.printTerms();
		lq.changeExpAndAddAsOr((s,l)->( (new String[] {"war","peace","green","sharm","barmaglot vs alice","fuck vs tedy bear","number"})[(new Random()).nextInt(7)]),"ENG");
		lq.print();	
		lq.printTerms();
		lq = new LQuery(lq.toString());
		lq.print();
		lq.printTerms();
		lq.changeExpAndAddAsOr((s,l)->( (new String[] {"war","peace","green","sharm","barmaglot vs alice","fuck vs tedy bear","number"})[(new Random()).nextInt(7)]),"ENG");
		lq = new LQuery(lq.toString());
		lq.print();
		lq.printTerms();
		lq.changeExpAndAddAsOr((s,l)->( (new String[] {"war","peace","green","sharm","barmaglot vs alice","fuck vs tedy bear","number"})[(new Random()).nextInt(7)]),"ENG");
		lq.print();
		lq.printTerms();

		
	}

//	@Test
//	void test1() {
//		String exp = "((((war) OR (WAR)) AND title:((\"peace\") OR (\"PEACE\")) NOT ((\"Black mamaba\") OR (\"BLACK MAMABA\"))) OR ( swstitle:((\"green*\") OR (\"GREEN*\")) NOT sub:((sharm) OR (SHARM)) AND usertag:((barmaglot vs alice ) OR (BARMAGLOT VS ALICE )) AND ((fuck vs tedy bear) OR (FUCK VS TEDY BEAR))) OR ((number) OR (NUMBER)))";
//		System.out.println(exp);
//		LQuery2 lq = new LQuery2(exp);
//		lq.print();
//		lq.printTerms();
//		lq.print();		
//	}
		
	@Test
	void test() {
		String exp = "(((war) AND title:(\"peace\")   NOT (\"Black mamaba\" )) OR ( swstitle:(\"green*\") NOT sub:(sharm) AND usertag:(barmaglot vs alice ) AND (fuck vs tedy bear)) OR (number))";
		System.out.println(exp);
		LQuery lq = new LQuery(exp);
		lq.print();
		lq.printTerms();
		lq.changeExpAndAddAsOr((s,l)->(s.toUpperCase()),"ENG");
		lq.print();
		lq = new LQuery(lq.toString());
		lq.print();
		lq.printTerms();
		lq.changeExpAndAddAsOr((s,l)->("xxxxx"),"ENG");
		lq.print();
		


		exp = "(((Green peace ) NOT (\"Black mamaba\" )) OR (sub:(Water flaw cintetic ) AND title:(Fingipul )))";
		lq = new LQuery(exp);
		lq.printTerms();
		lq.changeExpAndAddAsOr((s,l)->(s.toUpperCase()),"ENG");
		lq.print();

		exp = "(war)";
		lq = new LQuery(exp);
		lq.print();
		lq.printTerms();
		lq.changeExpAndAddAsOr((s,l)->(s.toUpperCase()),"ENG");
		lq.print();

		exp = "(Green peace vs mamba)";
		lq = new LQuery(exp);
		lq.printTerms();
		lq.changeExpAndAddAsOr((s,l)->(s.toUpperCase()),"ENG");
		lq.print();

		exp = "(((日 報 ) NOT (民 )) OR (sub:(人 ) AND title:(報 )))";
		lq = new LQuery(exp);
		lq.printTerms();
	}
	
	

	

}
