package primo_test.primo_test;

import java.util.ArrayList;
import java.util.Stack;





public class LQuery2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public ArrayList<ExpPrim> terms = new ArrayList<ExpPrim>();
	public ExpBool mainExp = null;
	
	public LQuery2(String query) {
		init(query);
	}
	
	private void init(String query) {
		int expc = 0;
		char[] qc = query.toCharArray();
		Stack<ExpBool> expbq = new Stack<ExpBool>();
		StringBuffer sb = null;
		ExpBool ee, ee1, ee2;
		ExpOp eop;
		for (int i = 0; i < qc.length; i++) {
			char c = qc[i];
			switch (c) {
			case '(':
				sb = new StringBuffer();
				ee=new ExpExp();
				expbq.push(ee);
				eop=new ExpOp();
				((ExpExp)ee).addExp(eop);;
				expc++;
				break;
			case ')':
				expc--;
				ee = expbq.pop();
				if(ee instanceof ExpExp) {
					if (sb.length() > 0) {
						ExpPrim ep = new ExpPrim();
						ep.setTerm(sb.toString());
						
						eop=((ExpExp) ee).getLastExp();
						if(eop !=null) {
							eop.setExp(ep);
						}else {
							eop=new ExpOp();
							((ExpExp) ee).addExp(eop);
							eop.setExp(ep);
						}
						
						sb = new StringBuffer();
						terms.add(ep);
					}else {
						if(!expbq.isEmpty()) {
							ee1=expbq.pop();
							eop=((ExpExp) ee1).getLastExp();
							if(eop ==null) {
								eop=new ExpOp();
							}	
							((ExpExp) ee1).addExp(eop);
							eop.setExp(ee);
							ee=ee1;
						}
					}
				
					expbq.push(ee);

				}
				if (expc == 0) {
					mainExp = expbq.pop();
				}
				break;
			case ' ':
				String t = sb.toString().trim();
				switch (t) {
				case "AND":
				case "OR":
				case "NOT":
					ee = expbq.pop();
					eop = ((ExpExp) ee).getLastExp();
					eop.setOper(t);
					
					expbq.push(ee);
					sb = new StringBuffer();
					break;

				default:
					sb.append(" ");				


				}
				break;
				
			case ':':
				if (sb.length() > 0) {
					ee=expbq.pop();
					eop=((ExpExp) ee).getLastExp();
					eop.setField(sb.toString());
					expbq.push(ee);
				}
				break;				
			case '"':
			default:
				sb.append(c);				
			}
			
		}
		
	}
	
	@Override
	public String toString() {
		return mainExp.toString();
	}

	public void print() {
		System.out.println("Main Exp:" + this);

	}

	public void printTerms() {
		for (ExpBool eb : this.terms) {
			System.out.println(eb);
		}
	}
	

	public interface ExpBool {
		
	}
	
	public class ExpExp implements ExpBool {
		private ArrayList<ExpOp> exps=new ArrayList<>();
		public void addExp(ExpOp e) {
			this.exps.add(e);
		}
		public ExpOp getLastExp() {
			if(this.exps.size()>0) {
				return this.exps.get(this.exps.size()-1);
			}else {
				return null;
			}
		}
		
		@Override
		public String toString() {
			StringBuilder sb=new StringBuilder(); 
					sb.append("(");
			for(ExpBool ee:this.exps) {
				sb.append(" ").append(ee.toString());
			}
			sb.append(")");
			return sb.toString();
		}
	}
	
	public class ExpOp implements ExpBool {
		private String field=null;
		private String oper=null;
		private ExpBool exp=null;
		
		public String getField() {
			return field;
		}

		public void setField(String field) {
			this.field = field;
		}

		public String getOper() {
			return oper;
		}

		public void setOper(String oper) {
			this.oper = oper;
		}

		public ExpBool getExp() {
			return exp;
		}

		public void setExp(ExpBool exp) {
			this.exp = exp;
		}

		@Override
		public String toString() {
			StringBuilder sb=new StringBuilder();
			if(field!=null) {
				sb.append(this.field).append(":");
			}
			if(this.exp !=null) {
				sb.append(exp.toString());
			}
			if(this.oper !=null ) {
				sb.append(" ").append(this.oper);
			}
			return sb.toString();
		}
	}
	
	public class ExpPrim implements ExpBool{
		private String term=null;
		public void setTerm(String term) {
			this.term = term;
		}
		
		public String getTerm() {
			return term;
		}
		
		@Override
		public String toString() {
			return this.getTerm();
		}
	}
	
	public class ExpExac  extends ExpPrim implements ExpBool {
		@Override
		public String toString() {
			return "\""+super.getTerm()+"\"";
		}
		
	}
	
}
	
	
