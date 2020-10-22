package primo_test.primo_test;

import java.util.Random;

public class TestThread extends Thread{
	
	String[] views= {"Auto1", "Auto2", "Auto3", "Auto4", "Auto5", "BC_VIEW", "DC_VIEW", "HV_VIEW"};
	String[] langs= {"eng", "fre", "ger", "sat", "psa", "dps", "dvs", "rtb"};
	TestRead tr;
	
	public TestThread(TestRead tr) {
		this.tr = tr;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		
		Random rd = new Random(System.currentTimeMillis());
		
		
		String v = views[rd.nextInt(views.length)];
		String l = langs[rd.nextInt(langs.length)];
		synchronized (this.tr) {
			System.out.println("VIEW:"+v+","+"LANG:"+l+","+"   "+ this.tr.getTranslation(v, l));
		}

		
		
	}
}
