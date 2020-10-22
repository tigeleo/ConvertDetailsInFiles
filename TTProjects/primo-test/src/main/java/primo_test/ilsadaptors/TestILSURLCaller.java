package primo_test.ilsadaptors;

public class TestILSURLCaller {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ILSDefaultAdaptor ilsda=new ILSDefaultAdaptor();
		
		String testUri1="http://ap01.alma.exlibrisgroup.com/view/uresolver/61MONASH_AU/openurl?ctx_enc=info:ofi/enc:UTF-8&ctx_id=10_1&ctx_tim=1506934071600&ctx_ver=Z39.88-2004&url_ctx_fmt=info:ofi/fmt:kev:mtx:ctx&url_ver=Z39.88-2004&rfr_id=info:sid/Elsevier:Scopus&req_id=&response_type=xml&ctx_enc=info:ofi/enc:UTF-8&rft.epage=2421&rft_val_fmt=info:ofi/fmt:kev:mtx:journal&rft.volume=37&ctx_ver=Z39.88-2003&rft.isbn=&isSerivcesPage=true&rft.aufirst=Q.&rft.pages=2415-2421&url_ctx_fmt=null&institution=MUA&rft.artnum=&rft.spage=2415&rft.issn=03014851&rft_id=info:doi/10.1007/s11033-009-9752-7&rft.issue=5&rft.aulast=Li&url_ver=Z39.88-2003&rft.date=2010&rft.atitle=Characterization of a novel human CDK5 splicing variant that inhibits Wnt/?-catenin signaling&rft.title=Molecular Biology Reports&&svc_dat=CTO&user_ip=130.194.20.173";
		String testUri2="http://ap01.alma.exlibrisgroup.com/view/uresolver/61UNSW_INST/openurl?ctx_enc=info:ofi/enc:UTF-8&ctx_id=10_1&ctx_tim=1508076981212&ctx_ver=Z39.88-2004&url_ctx_fmt=info:ofi/fmt:kev:mtx:ctx&url_ver=Z39.88-2004&rfr_id=&req_id=&response_type=xml&date=2013&aulast=Clark&issue=6&isSerivcesPage=true&spage=2087&auinit=D&title=?The ?American economic review&atitle=The Effect of Education on Adult Mortality and Health: Evidence from Britain&url_ctx_fmt=null&sid=google&volume=103&institution=61UNSW_INST&&svc_dat=CTO&user_ip=186.52.188.69";
			
		
		ilsda.executeService("alma_get_services_info", testUri1);
		ilsda.executeService("alma_get_services_info", testUri2);
	}

}
