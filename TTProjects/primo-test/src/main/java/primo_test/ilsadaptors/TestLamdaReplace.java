package primo_test.ilsadaptors;

import java.util.ArrayList;
import java.util.List;

public class TestLamdaReplace {
public static void main(String[] args) {
	List<String> urls = new ArrayList<>();
	
	urls.add("https://northumbria.userservices.exlibrisgroup.com/view/uresolver/44UON_INST/openurl?ctx_enc=info:ofi/enc:UTF-8&ctx_id=10_1&ctx_tim=2018-01-08T13%3A45%3A42IST&ctx_ver=Z39.88-2004&url_ctx_fmt=info:ofi/fmt:kev:mtx:ctx&url_ver=Z39.88-2004&rfr_id=info:sid/primo.exlibrisgroup.com-oxford_reference&req_id=&rft_val_fmt=info:ofi/fmt:kev:mtx:journal&rft.genre=article&rft.atitle=life&rft.jtitle=&rft.btitle=New%20Oxford%20Rhyming%20Dictionary&rft.aulast=&rft.auinit=&rft.auinit1=&rft.auinitm=&rft.ausuffix=&rft.au=&rft.aucorp=&rft.date=2012&rft.volume=&rft.issue=&rft.part=&rft.quarter=&rft.ssn=&rft.spage=&rft.epage=&rft.pages=&rft.artnum=&rft.issn=&rft.eissn=&rft.isbn=9780191744310&rft.sici=&rft.coden=&rft_id=info:doi/&rft.object_id=&rft.eisbn=&rft.edition=2%20ed.&rft.pub=Oxford%20University%20Press&rft.place=&rft.series=&rft.stitle=&rft.bici=&rft_id=info:bibcode/&rft_id=info:hdl/&rft_id=info:lccn/&rft_id=info:oclcnum/&rft_id=info:pmid/&rft_id=info:eric/((addata/eric}}&rft_dat=<oxford_reference>10.1093/acref/9780199652464.013.21706</oxford_reference>,language=eng,view=northumbria&svc_dat=viewit&env_type=test");
	urls.add("https://northumbria.userservices.exlibrisgroup.com/view/uresolver/44UON_INST/openurl?ctx_enc=info:ofi/enc:UTF-8&ctx_id=10_1&ctx_tim=2018-01-08T14%3A20%3A55IST&ctx_ver=Z39.88-2004&url_ctx_fmt=info:ofi/fmt:kev:mtx:ctx&url_ver=Z39.88-2004&rfr_id=info:sid/primo.exlibrisgroup.com-tayfranc&req_id=&rft_val_fmt=info:ofi/fmt:kev:mtx:journal&rft.genre=article&rft.atitle=Life?&rft.jtitle=Journal%20of%20Biomolecular%20Structure%20and%20Dynamics&rft.btitle=&rft.aulast=Mishra&rft.auinit=&rft.auinit1=&rft.auinitm=&rft.ausuffix=&rft.au=Mishra,%20Seema&rft.aucorp=&rft.date=20120201&rft.volume=29&rft.issue=4&rft.part=&rft.quarter=&rft.ssn=&rft.spage=633&rft.epage=634&rft.pages=633-634&rft.artnum=&rft.issn=0739-1102&rft.eissn=1538-0254&rft.isbn=&rft.sici=&rft.coden=Journal%20of%20Biomolecular%20Structure%20and%20Dynamics,%20Vol.%2029,%20No.%204,%202012:%20pp.%20633%E2%80%93634&rft_id=info:doi/10.1080/073911012010525011&rft.object_id=&rft.eisbn=&rft.edition=&rft.pub=Taylor%20&%20Francis%20Group&rft.place=&rft.series=&rft.stitle=&rft.bici=&rft_id=info:bibcode/&rft_id=info:hdl/&rft_id=info:lccn/&rft_id=info:oclcnum/&rft_id=info:pmid/&rft_id=info:eric/((addata/eric}}&rft_dat=<tayfranc>10.1080/073911012010525011</tayfranc>,language=eng,view=northumbria&svc_dat=viewit&env_type=test");
	urls.add("https://northumbria.userservices.exlibrisgroup.com/view/uresolver/44UON_INST/openurl?ctx_enc=info:ofi/enc:UTF-8&ctx_id=10_1&ctx_tim=2018-01-08T14%3A20%3A55IST&ctx_ver=Z39.88-2004&url_ctx_fmt=info:ofi/fmt:kev:mtx:ctx&url_ver=Z39.88-2004&rfr_id=info:sid/primo.exlibrisgroup.com-oxford&req_id=&rft_val_fmt=info:ofi/fmt:kev:mtx:book&rft.genre=document&rft.atitle=Life&rft.jtitle=Literary%20Imagination&rft.btitle=&rft.aulast=N%E2%80%99gana&rft.auinit=&rft.auinit1=&rft.auinitm=&rft.ausuffix=&rft.au=N%E2%80%99gana,%20Y%C3%A9o&rft.aucorp=&rft.date=201607&rft.volume=18&rft.issue=2&rft.part=&rft.quarter=&rft.ssn=&rft.spage=100&rft.epage=100&rft.pages=100-100&rft.artnum=&rft.issn=1523-9012&rft.eissn=1752-6566&rft.isbn=&rft.sici=&rft.coden=&rft_id=info:doi/10.1093/litimag/imw009&rft.object_id=&rft.eisbn=&rft.edition=&rft.pub=Oxford%20University%20Press&rft.place=&rft.series=&rft.stitle=&rft.bici=&rft_id=info:bibcode/&rft_id=info:hdl/&rft_id=info:lccn/&rft_id=info:oclcnum/&rft_id=info:pmid/&rft_id=info:eric/((addata/eric}}&rft_dat=<oxford>10.1093/litimag/imw009</oxford>,language=eng,view=northumbria&svc_dat=viewit&env_type=test");
	
	
	urls=urls.stream().map(e -> e.replaceAll("<", "%3C").replaceAll(">", "%3E")).collect(java.util.stream.Collectors.toList());
	
	urls.forEach(System.out::println);
			
}
}
