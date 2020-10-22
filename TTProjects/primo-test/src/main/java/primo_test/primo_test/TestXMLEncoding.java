package primo_test.primo_test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.StringReader;
import java.io.StringWriter;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.xmlbeans.XmlException;
import org.junit.jupiter.api.Test;
import org.xml.sax.InputSource;

import noNamespace.PrimoNMBibDocument;
import noNamespace.PrimoNMBibDocument.PrimoNMBib;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;


import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

class TestXMLEncoding {
	private static String xmlContentValid = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
			"<record>\r\n" + 
			"<control>\r\n" + 
			"<sourcerecordid>11185303160002791</sourcerecordid> \r\n" + 
			"<sourceid>972ELLAD_ALMA</sourceid> \r\n" + 
			"<recordid>972ELLAD_ALMA11185303160002791</recordid> \r\n" + 
			"<originalsourceid>972ELLAD</originalsourceid> \r\n" + 
			"<sourceformat>MARC21</sourceformat> \r\n" + 
			"<sourcesystem>Alma</sourcesystem> \r\n" + 
			"<almaid>972HAI_MAIN:11185303160002791</almaid> \r\n" + 
			"<colldiscovery>$$Titem$$D81173584980002791$$I</colldiscovery> \r\n" + 
			"</control>\r\n" + 
			"<display>\r\n" + 
			"<type>book</type> \r\n" + 
			"<title>&#x1d609;-orbits of type (3², 1²ⁿ⁻⁶) in nilradical of Lie Algebra of type &#x1d436;&#x1d45b;</title> \r\n" + 
			"<creator>Yazeed Maree מרעי, יזיד</creator> \r\n" + 
			"<contributor>Anna Melnikov מלניקוב, אנה; Universiṭat Ḥefah. ha-Ḥug le-matemaṭiḳah; אוניברסיטת חיפה. החוג למתמטיקה</contributor> \r\n" + 
			"<publisher>Haifa : University of Haifa</publisher> \r\n" + 
			"<creationdate>2019</creationdate> \r\n" + 
			"<format>1 online resource (iv, 47 pagves) : illustrations, PDF.</format> \r\n" + 
			"<subject>Lie groups lat; חבורות לי heb; Lie algebras lat; g Symmetric spaces lat; g Topological groups lat; Nilpotent groups lat; קבוצות נילפוטנטיות heb; g Finite groups lat; Algebras, Linear lat; אלגברה ליניארית heb; g Algebra, Universal lat; g Generalized spaces lat; g Mathematical analysis lat; Calculus of operations lat; Line geometry lat; Topology lat; Academic theses lat; תזות אקדמיות heb; Informational works g lat</subject> \r\n" + 
			"<language>eng</language> \r\n" + 
			"<source>972ELLAD_ALMA</source> \r\n" + 
			"</display>\r\n" + 
			"<links>\r\n" + 
			"<thumbnail>$$Talma_thumb2</thumbnail> \r\n" + 
			"</links>\r\n" + 
			"<search>\r\n" + 
			"<creatorcontrib>Yazeed  Maree  author</creatorcontrib> \r\n" + 
			"<creatorcontrib>Yazeed  Maree</creatorcontrib> \r\n" + 
			"<creatorcontrib>מרעי, יזיד</creatorcontrib> \r\n" + 
			"<creatorcontrib>Maree, Y</creatorcontrib> \r\n" + 
			"<creatorcontrib>מרעי</creatorcontrib> \r\n" + 
			"<creatorcontrib>Yazeed Maree ; supervised by Anna Melnikov.</creatorcontrib> \r\n" + 
			"<creatorcontrib>Anna,  Melnikov  degree supervisor.</creatorcontrib> \r\n" + 
			"<creatorcontrib>Anna  Melnikov</creatorcontrib> \r\n" + 
			"<creatorcontrib>מלניקוב, אנה</creatorcontrib> \r\n" + 
			"<creatorcontrib>Melnikov, A</creatorcontrib> \r\n" + 
			"<creatorcontrib>מלניקוב</creatorcontrib> \r\n" + 
			"<creatorcontrib>Universiṭat Ḥefah. ha-Ḥug le-matemaṭiḳah. degree granting institution.</creatorcontrib> \r\n" + 
			"<creatorcontrib>Universiṭat Ḥefah. ha-Ḥug le-matemaṭiḳah</creatorcontrib> \r\n" + 
			"<creatorcontrib>אוניברסיטת חיפה. החוג למתמטיקה</creatorcontrib> \r\n" + 
			"<creatorcontrib>Universiṭat Ḥefah. Ḥug le-matemaṭiḳah</creatorcontrib> \r\n" + 
			"<creatorcontrib>Universitat Hefah. ha-Hug le-matematikah (M.A.)</creatorcontrib> \r\n" + 
			"<creatorcontrib>Universitat Hefah. ha-Hug le-matematikah (Ph.D.)</creatorcontrib> \r\n" + 
			"<creatorcontrib>University of Haifa. Department of Mathematics (M.A.)</creatorcontrib> \r\n" + 
			"<creatorcontrib>University of Haifa. Department of Mathematics (Ph.D.)</creatorcontrib> \r\n" + 
			"<creatorcontrib>Universiṭat Ḥefah. ha-Ḥug le-matemaṭiḳah</creatorcontrib> \r\n" + 
			"<creatorcontrib>אוניברסיטת חיפה. חוג למתמטיקה</creatorcontrib> \r\n" + 
			"<creatorcontrib>אוניברסיטת חיפה. החוג למתמטיקה</creatorcontrib> \r\n" + 
			"<title>&#x1d609;-orbits of type (3², 1²ⁿ⁻⁶) in nilradical of Lie Algebra of type &#x1d436;&#x1d45b; /</title> \r\n" + 
			"<subject>Algebras, Linear lat</subject> \r\n" + 
			"<subject>Nilpotent groups lat</subject> \r\n" + 
			"<subject>Lie groups lat</subject> \r\n" + 
			"<subject>חבורות לי heb</subject> \r\n" + 
			"<subject>Groups, Lie lat</subject> \r\n" + 
			"<subject>Lie algebras lat</subject> \r\n" + 
			"<subject>g Symmetric spaces lat</subject> \r\n" + 
			"<subject>g Topological groups lat</subject> \r\n" + 
			"<subject>קבוצות נילפוטנטיות heb</subject> \r\n" + 
			"<subject>nne Groups, Nilpotent lat</subject> \r\n" + 
			"<subject>g Finite groups lat</subject> \r\n" + 
			"<subject>אלגברה ליניארית heb</subject> \r\n" + 
			"<subject>Linear algebra lat</subject> \r\n" + 
			"<subject>g Algebra, Universal lat</subject> \r\n" + 
			"<subject>g Generalized spaces lat</subject> \r\n" + 
			"<subject>g Mathematical analysis lat</subject> \r\n" + 
			"<subject>Calculus of operations lat</subject> \r\n" + 
			"<subject>Line geometry lat</subject> \r\n" + 
			"<subject>Topology lat</subject> \r\n" + 
			"<subject>Academic theses lat</subject> \r\n" + 
			"<subject>תזות אקדמיות heb</subject> \r\n" + 
			"<subject>Academic dissertations lat</subject> \r\n" + 
			"<subject>Bachelor's theses lat</subject> \r\n" + 
			"<subject>Diploma theses lat</subject> \r\n" + 
			"<subject>Dissertations, Academic (PhD) lat</subject> \r\n" + 
			"<subject>Dissertations, Academic (Master) lat</subject> \r\n" + 
			"<subject>Doctoral dissertations lat</subject> \r\n" + 
			"<subject>Doctoral theses lat</subject> \r\n" + 
			"<subject>Graduate dissertations lat</subject> \r\n" + 
			"<subject>Graduate theses lat</subject> \r\n" + 
			"<subject>Licentiate dissertations lat</subject> \r\n" + 
			"<subject>Licentiate theses lat</subject> \r\n" + 
			"<subject>Master's dissertations lat</subject> \r\n" + 
			"<subject>Master's theses lat</subject> \r\n" + 
			"<subject>Ph. D. dissertations lat</subject> \r\n" + 
			"<subject>Ph. D. theses lat</subject> \r\n" + 
			"<subject>Senior projects lat</subject> \r\n" + 
			"<subject>Senior theses lat</subject> \r\n" + 
			"<subject>Theses, Academic lat</subject> \r\n" + 
			"<subject>Undergraduate theses lat</subject> \r\n" + 
			"<subject>תיזות אקדמיות heb</subject> \r\n" + 
			"<subject>דיסרטציות אקדמיות heb</subject> \r\n" + 
			"<subject>עבודות גמר heb</subject> \r\n" + 
			"<subject>Informational works g lat</subject> \r\n" + 
			"<general>[University of Haifa],</general> \r\n" + 
			"<general>M.A. University of Haifa, Faculty of Natural Sciences, Dept. of Mathematics, 2019</general> \r\n" + 
			"<sourceid>972ELLAD_ALMA</sourceid> \r\n" + 
			"<recordid>972ELLAD_ALMA11185303160002791</recordid> \r\n" + 
			"<rsrctype>book</rsrctype> \r\n" + 
			"<creationdate>2019</creationdate> \r\n" + 
			"<startdate>20190101</startdate> \r\n" + 
			"<enddate>20191231</enddate> \r\n" + 
			"<addsrcrecordid>9919479786502791</addsrcrecordid> \r\n" + 
			"<searchscope>972ELLAD_ALMA</searchscope> \r\n" + 
			"<searchscope>collections</searchscope> \r\n" + 
			"<scope>972ELLAD_ALMA</scope> \r\n" + 
			"<scope>collections</scope> \r\n" + 
			"<alttitle>מסלולי בורל מסוג (3², 1²ⁿ⁻⁶) בנילרדיקל של אלגברת לי מסוג &#x1d436;&#x1d45b; /</alttitle> \r\n" + 
			"<alttitle>Borel orbits of type (3², 1²ⁿ⁻⁶) in nilradical of Lie Algebra of type &#x1d436;&#x1d45b;.</alttitle> \r\n" + 
			"<cdparentID>81173584980002791</cdparentID> \r\n" + 
			"</search>\r\n" + 
			"<sort>\r\n" + 
			"<title>&#x1d609;-orbits of type (3², 1²ⁿ⁻⁶) in nilradical of Lie Algebra of type &#x1d436;&#x1d45b; /</title> \r\n" + 
			"<creationdate>2019</creationdate> \r\n" + 
			"<author>Maree, Yazeed</author> \r\n" + 
			"</sort>\r\n" + 
			"<facets>\r\n" + 
			"<language>eng</language> \r\n" + 
			"<creationdate>2019</creationdate> \r\n" + 
			"<topic>Lie groups lat</topic> \r\n" + 
			"<topic>חבורות לי heb</topic> \r\n" + 
			"<topic>Lie algebras lat</topic> \r\n" + 
			"<topic>g Symmetric spaces lat</topic> \r\n" + 
			"<topic>g Topological groups lat</topic> \r\n" + 
			"<topic>Nilpotent groups lat</topic> \r\n" + 
			"<topic>קבוצות נילפוטנטיות heb</topic> \r\n" + 
			"<topic>g Finite groups lat</topic> \r\n" + 
			"<topic>Algebras, Linear lat</topic> \r\n" + 
			"<topic>אלגברה ליניארית heb</topic> \r\n" + 
			"<topic>g Algebra, Universal lat</topic> \r\n" + 
			"<topic>g Generalized spaces lat</topic> \r\n" + 
			"<topic>g Mathematical analysis lat</topic> \r\n" + 
			"<topic>Calculus of operations lat</topic> \r\n" + 
			"<topic>Line geometry lat</topic> \r\n" + 
			"<topic>Topology lat</topic> \r\n" + 
			"<collection>החוג למתמטיקה - Department of Mathematics (MA)</collection> \r\n" + 
			"<toplevel>online_resources</toplevel> \r\n" + 
			"<prefilter>books</prefilter> \r\n" + 
			"<rsrctype>books</rsrctype> \r\n" + 
			"<creatorcontrib>Maree, Yazeed</creatorcontrib> \r\n" + 
			"<creatorcontrib>מרעי, יזיד</creatorcontrib> \r\n" + 
			"<creatorcontrib>Melnikov, Anna</creatorcontrib> \r\n" + 
			"<creatorcontrib>מלניקוב, אנה</creatorcontrib> \r\n" + 
			"<creatorcontrib>Universiṭat Ḥefah. ha-Ḥug le-matemaṭiḳah</creatorcontrib> \r\n" + 
			"<creatorcontrib>אוניברסיטת חיפה. החוג למתמטיקה</creatorcontrib> \r\n" + 
			"<genre>Academic theses</genre> \r\n" + 
			"<genre>תזות אקדמיות</genre> \r\n" + 
			"<genre>Academic dissertations</genre> \r\n" + 
			"<genre>Bachelor's theses</genre> \r\n" + 
			"<genre>Diploma theses</genre> \r\n" + 
			"<genre>Dissertations, Academic (PhD)</genre> \r\n" + 
			"<genre>Dissertations, Academic (Master)</genre> \r\n" + 
			"<genre>Doctoral dissertations</genre> \r\n" + 
			"<genre>Doctoral theses</genre> \r\n" + 
			"<genre>Graduate dissertations</genre> \r\n" + 
			"<genre>Graduate theses</genre> \r\n" + 
			"<genre>Licentiate dissertations</genre> \r\n" + 
			"<genre>Licentiate theses</genre> \r\n" + 
			"<genre>Master's dissertations</genre> \r\n" + 
			"<genre>Master's theses</genre> \r\n" + 
			"<genre>Ph. D. dissertations</genre> \r\n" + 
			"<genre>Ph. D. theses</genre> \r\n" + 
			"<genre>Senior projects</genre> \r\n" + 
			"<genre>Senior theses</genre> \r\n" + 
			"<genre>Theses, Academic</genre> \r\n" + 
			"<genre>Undergraduate theses</genre> \r\n" + 
			"<genre>תיזות אקדמיות</genre> \r\n" + 
			"<genre>דיסרטציות אקדמיות</genre> \r\n" + 
			"<genre>עבודות גמר</genre> \r\n" + 
			"<genre>Informational works</genre> \r\n" + 
			"<atoz>Others</atoz> \r\n" + 
			"<newrecords>20201019_141</newrecords></facets>\r\n" + 
			"<dedup>\r\n" + 
			"<t>1</t> \r\n" + 
			"<c3>&#x1d609;orbitsoftype3212ⁿ⁻⁶innilradicalofliealgebraoftype&#x1d436;&#x1d45b;</c3> \r\n" + 
			"<c4>2019</c4> \r\n" + 
			"<c5>9919479786502791</c5> \r\n" + 
			"<f5>&#x1d609;orbitsoftype3212ⁿ⁻⁶innilradicalofliealgebraoftype&#x1d436;&#x1d45b;</f5> \r\n" + 
			"<f6>2019</f6> \r\n" + 
			"<f7>&#x1d609; orbits of type 32 12ⁿ⁻⁶ in nilradical of lie algebra of type &#x1d436;&#x1d45b;</f7> \r\n" + 
			"<f8>is</f8> \r\n" + 
			"<f9>1 online resource (iv, 47 pagves) :</f9> \r\n" + 
			"<f10>university of haifa</f10> \r\n" + 
			"<f11>maree yazeed</f11> \r\n" + 
			"<f20>9919479786502791</f20> \r\n" + 
			"</dedup>\r\n" + 
			"<frbr>\r\n" + 
			"<t>1</t> \r\n" + 
			"<k1>$$Kmaree yazeed$$AA</k1> \r\n" + 
			"<k3>$$K&#x1d609; orbits of type 32 12ⁿ⁻⁶ in nilradical of lie algebra of type &#x1d436;&#x1d45b;$$AT</k3> \r\n" + 
			"</frbr>\r\n" + 
			"<delivery>\r\n" + 
			"<delcategory>Alma-D</delcategory> \r\n" + 
			"</delivery>\r\n" + 
			"<ranking>\r\n" + 
			"<booster1>1</booster1> \r\n" + 
			"<booster2>1</booster2> \r\n" + 
			"</ranking>\r\n" + 
			"<addata>\r\n" + 
			"<aulast>Maree</aulast> \r\n" + 
			"<aulast>מרעי, יזיד</aulast> \r\n" + 
			"<aulast>Melnikov</aulast> \r\n" + 
			"<aulast>מלניקוב, אנה</aulast> \r\n" + 
			"<aufirst>Yazeed</aufirst> \r\n" + 
			"<au>Maree, Yazeed</au> \r\n" + 
			"<addau>Melnikov, Anna</addau> \r\n" + 
			"<addau>מלניקוב, אנה</addau> \r\n" + 
			"<addau>Universiṭat Ḥefah. ha-Ḥug le-matemaṭiḳah</addau> \r\n" + 
			"<addau>אוניברסיטת חיפה. החוג למתמטיקה</addau> \r\n" + 
			"<btitle>&#x1d609;-orbits of type (3², 1²ⁿ⁻⁶) in nilradical of Lie Algebra of type &#x1d436;&#x1d45b;</btitle> \r\n" + 
			"<addtitle>מסלולי בורל מסוג (3², 1²ⁿ⁻⁶) בנילרדיקל של אלגברת לי מסוג &#x1d436;&#x1d45b;</addtitle> \r\n" + 
			"<date>2019</date> \r\n" + 
			"<risdate>2019</risdate> \r\n" + 
			"<format>dissertation</format> \r\n" + 
			"<genre>book</genre> \r\n" + 
			"<ristype>BOOK</ristype> \r\n" + 
			"<notes>Includes bibliographical references (page 47).</notes> \r\n" + 
			"<mis1>11185303160002791</mis1> \r\n" + 
			"</addata>\r\n" + 
			"<browse>\r\n" + 
			"<author>$$DMaree, Yazeed lat$$EMaree, Yazeed lat$$I41-NLI-987007413398405171$$PN</author> \r\n" + 
			"<author>$$DMaree, Yazeed lat$$EMaree, Yazeed lat$$I41-NLI-987007413398405171$$PY</author> \r\n" + 
			"<author>$$Dמרעי, יזיד heb$$Eמרעי, יזיד heb$$I41-NLI-987007413398405171$$PY</author> \r\n" + 
			"<author>$$DMelnikov, Anna, lat$$EMelnikov, Anna, lat$$I41-NLI-987007430272905171$$PN</author> \r\n" + 
			"<author>$$DMelnikov, Anna lat$$EMelnikov, Anna lat$$I41-NLI-987007430272905171$$PY</author> \r\n" + 
			"<author>$$Dמלניקוב, אנה heb$$Eמלניקוב, אנה heb$$I41-NLI-987007430272905171$$PY</author> \r\n" + 
			"<author>$$DUniversiṭat Ḥefah. ha-Ḥug le-matemaṭiḳah. lat$$EUniversiṭat Ḥefah. ha-Ḥug le-matemaṭiḳah. lat$$I41-NLI-987007587969505171$$PN</author> \r\n" + 
			"<author>$$DUniversiṭat Ḥefah. ha-Ḥug le-matemaṭiḳah lat$$EUniversiṭat Ḥefah. ha-Ḥug le-matemaṭiḳah lat$$I41-NLI-987007587969505171$$PY</author> \r\n" + 
			"<author>$$Dאוניברסיטת חיפה. החוג למתמטיקה heb$$Eאוניברסיטת חיפה. החוג למתמטיקה heb$$I41-NLI-987007587969505171$$PY</author> \r\n" + 
			"<author>$$DUniversiṭat Ḥefah. Ḥug le-matemaṭiḳah lat$$EUniversiṭat Ḥefah. Ḥug le-matemaṭiḳah lat$$I41-NLI-987007587969505171$$PN</author> \r\n" + 
			"<author>$$DUniversitat Hefah. ha-Hug le-matematikah (M.A.) lat$$EUniversitat Hefah. ha-Hug le-matematikah (M.A.) lat$$I41-NLI-987007587969505171$$PN</author> \r\n" + 
			"<author>$$DUniversitat Hefah. ha-Hug le-matematikah (Ph.D.) lat$$EUniversitat Hefah. ha-Hug le-matematikah (Ph.D.) lat$$I41-NLI-987007587969505171$$PN</author> \r\n" + 
			"<author>$$DUniversity of Haifa. Department of Mathematics (M.A.) lat$$EUniversity of Haifa. Department of Mathematics (M.A.) lat$$I41-NLI-987007587969505171$$PN</author> \r\n" + 
			"<author>$$DUniversity of Haifa. Department of Mathematics (Ph.D.) lat$$EUniversity of Haifa. Department of Mathematics (Ph.D.) lat$$I41-NLI-987007587969505171$$PN</author> \r\n" + 
			"<author>$$DUniversiṭat Ḥefah. ha-Ḥug le-matemaṭiḳah lat$$EUniversiṭat Ḥefah. ha-Ḥug le-matemaṭiḳah lat$$I41-NLI-987007587969505171$$PN</author> \r\n" + 
			"<author>$$Dאוניברסיטת חיפה. חוג למתמטיקה heb$$Eאוניברסיטת חיפה. חוג למתמטיקה heb$$I41-NLI-987007587969505171$$PN</author> \r\n" + 
			"<author>$$Dאוניברסיטת חיפה. החוג למתמטיקה heb$$Eאוניברסיטת חיפה. החוג למתמטיקה heb$$I41-NLI-987007587969505171$$PN</author> \r\n" + 
			"<title>$$D&#x1d609;-orbits of type (3², 1²ⁿ⁻⁶) in nilradical of Lie Algebra of type &#x1d436;&#x1d45b;$$E&#x1d609;-orbits of type (3², 1²ⁿ⁻⁶) in nilradical of Lie Algebra of type &#x1d436;&#x1d45b;</title> \r\n" + 
			"<title>$$Dמסלולי בורל מסוג (3², 1²ⁿ⁻⁶) בנילרדיקל של אלגברת לי מסוג &#x1d436;&#x1d45b;$$Eמסלולי בורל מסוג (3², 1²ⁿ⁻⁶) בנילרדיקל של אלגברת לי מסוג &#x1d436;&#x1d45b;</title> \r\n" + 
			"<title>$$DBorel orbits of type (3², 1²ⁿ⁻⁶) in nilradical of Lie Algebra of type &#x1d436;&#x1d45b;$$EBorel orbits of type (3², 1²ⁿ⁻⁶) in nilradical of Lie Algebra of type &#x1d436;&#x1d45b;</title> \r\n" + 
			"<subject>$$DAlgebras, Linear$$EAlgebras, Linear$$TNLI$$I41-NLI-987007293931805171$$PN</subject> \r\n" + 
			"<subject>$$DNilpotent groups$$ENilpotent groups$$TNLI$$I41-NLI-987007543476805171$$PN</subject> \r\n" + 
			"<subject>$$DLie groups$$ELie groups$$TNLI$$I41-NLI-987007529233305171$$PN</subject> \r\n" + 
			"<subject>$$DLie groups$$ELie groups$$TNLI$$I41-NLI-987007529233305171$$PY</subject> \r\n" + 
			"<subject>$$Dחבורות לי$$Eחבורות לי$$TNLI$$I41-NLI-987007529233305171$$PY</subject> \r\n" + 
			"<subject>$$DGroups, Lie$$EGroups, Lie$$TNLI$$I41-NLI-987007529233305171$$PN</subject> \r\n" + 
			"<subject>$$DLie algebras$$ELie algebras$$TNLI$$I41-NLI-987007529233305171$$PR</subject> \r\n" + 
			"<subject>$$DSymmetric spaces$$ESymmetric spaces$$TNLI$$I41-NLI-987007529233305171$$PR</subject> \r\n" + 
			"<subject>$$DTopological groups$$ETopological groups$$TNLI$$I41-NLI-987007529233305171$$PR</subject> \r\n" + 
			"<subject>$$DNilpotent groups$$ENilpotent groups$$TNLI$$I41-NLI-987007543476805171$$PY</subject> \r\n" + 
			"<subject>$$Dקבוצות נילפוטנטיות$$Eקבוצות נילפוטנטיות$$TNLI$$I41-NLI-987007543476805171$$PY</subject> \r\n" + 
			"<subject>$$DGroups, Nilpotent$$EGroups, Nilpotent$$TNLI$$I41-NLI-987007543476805171$$PN</subject> \r\n" + 
			"<subject>$$DFinite groups$$EFinite groups$$TNLI$$I41-NLI-987007543476805171$$PR</subject> \r\n" + 
			"<subject>$$DAlgebras, Linear$$EAlgebras, Linear$$TNLI$$I41-NLI-987007293931805171$$PY</subject> \r\n" + 
			"<subject>$$Dאלגברה ליניארית$$Eאלגברה ליניארית$$TNLI$$I41-NLI-987007293931805171$$PY</subject> \r\n" + 
			"<subject>$$DLinear algebra$$ELinear algebra$$TNLI$$I41-NLI-987007293931805171$$PN</subject> \r\n" + 
			"<subject>$$DAlgebra, Universal$$EAlgebra, Universal$$TNLI$$I41-NLI-987007293931805171$$PR</subject> \r\n" + 
			"<subject>$$DGeneralized spaces$$EGeneralized spaces$$TNLI$$I41-NLI-987007293931805171$$PR</subject> \r\n" + 
			"<subject>$$DMathematical analysis$$EMathematical analysis$$TNLI$$I41-NLI-987007293931805171$$PR</subject> \r\n" + 
			"<subject>$$DCalculus of operations$$ECalculus of operations$$TNLI$$I41-NLI-987007293931805171$$PR</subject> \r\n" + 
			"<subject>$$DLine geometry$$ELine geometry$$TNLI$$I41-NLI-987007293931805171$$PR</subject> \r\n" + 
			"<subject>$$DTopology$$ETopology$$TNLI$$I41-NLI-987007293931805171$$PR</subject> \r\n" + 
			"<subject>$$DAcademic theses$$EAcademic theses$$TNLI$$I41-NLI-987007574316205171$$PN</subject> \r\n" + 
			"<subject>$$DAcademic theses$$EAcademic theses$$TNLI$$I41-NLI-987007574316205171$$PY</subject> \r\n" + 
			"<subject>$$Dתזות אקדמיות$$Eתזות אקדמיות$$TNLI$$I41-NLI-987007574316205171$$PY</subject> \r\n" + 
			"<subject>$$DAcademic dissertations$$EAcademic dissertations$$TNLI$$I41-NLI-987007574316205171$$PN</subject> \r\n" + 
			"<subject>$$DBachelor's theses$$EBachelor's theses$$TNLI$$I41-NLI-987007574316205171$$PN</subject> \r\n" + 
			"<subject>$$DDiploma theses$$EDiploma theses$$TNLI$$I41-NLI-987007574316205171$$PN</subject> \r\n" + 
			"<subject>$$DDissertations, Academic (PhD)$$EDissertations, Academic (PhD)$$TNLI$$I41-NLI-987007574316205171$$PN</subject> \r\n" + 
			"<subject>$$DDissertations, Academic (Master)$$EDissertations, Academic (Master)$$TNLI$$I41-NLI-987007574316205171$$PN</subject> \r\n" + 
			"<subject>$$DDoctoral dissertations$$EDoctoral dissertations$$TNLI$$I41-NLI-987007574316205171$$PN</subject> \r\n" + 
			"<subject>$$DDoctoral theses$$EDoctoral theses$$TNLI$$I41-NLI-987007574316205171$$PN</subject> \r\n" + 
			"<subject>$$DGraduate dissertations$$EGraduate dissertations$$TNLI$$I41-NLI-987007574316205171$$PN</subject> \r\n" + 
			"<subject>$$DGraduate theses$$EGraduate theses$$TNLI$$I41-NLI-987007574316205171$$PN</subject> \r\n" + 
			"<subject>$$DLicentiate dissertations$$ELicentiate dissertations$$TNLI$$I41-NLI-987007574316205171$$PN</subject> \r\n" + 
			"<subject>$$DLicentiate theses$$ELicentiate theses$$TNLI$$I41-NLI-987007574316205171$$PN</subject> \r\n" + 
			"<subject>$$DMaster's dissertations$$EMaster's dissertations$$TNLI$$I41-NLI-987007574316205171$$PN</subject> \r\n" + 
			"<subject>$$DMaster's theses$$EMaster's theses$$TNLI$$I41-NLI-987007574316205171$$PN</subject> \r\n" + 
			"<subject>$$DPh. D. dissertations$$EPh. D. dissertations$$TNLI$$I41-NLI-987007574316205171$$PN</subject> \r\n" + 
			"<subject>$$DPh. D. theses$$EPh. D. theses$$TNLI$$I41-NLI-987007574316205171$$PN</subject> \r\n" + 
			"<subject>$$DSenior projects$$ESenior projects$$TNLI$$I41-NLI-987007574316205171$$PN</subject> \r\n" + 
			"<subject>$$DSenior theses$$ESenior theses$$TNLI$$I41-NLI-987007574316205171$$PN</subject> \r\n" + 
			"<subject>$$DTheses, Academic$$ETheses, Academic$$TNLI$$I41-NLI-987007574316205171$$PN</subject> \r\n" + 
			"<subject>$$DUndergraduate theses$$EUndergraduate theses$$TNLI$$I41-NLI-987007574316205171$$PN</subject> \r\n" + 
			"<subject>$$Dתיזות אקדמיות$$Eתיזות אקדמיות$$TNLI$$I41-NLI-987007574316205171$$PN</subject> \r\n" + 
			"<subject>$$Dדיסרטציות אקדמיות$$Eדיסרטציות אקדמיות$$TNLI$$I41-NLI-987007574316205171$$PN</subject> \r\n" + 
			"<subject>$$Dעבודות גמר$$Eעבודות גמר$$TNLI$$I41-NLI-987007574316205171$$PN</subject> \r\n" + 
			"<subject>$$DInformational works$$EInformational works$$TNLI$$I41-NLI-987007574316205171$$PR</subject> \r\n" + 
			"</browse>\r\n" + 
			"</record>";
	
	
	private static String xmlContentNotEncoded = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
			"<record>\r\n" + 
			"<control>\r\n" + 
			"<almaid>$$V972HAI_MAIN:11185303160002791$$O972ELLAD_ALMA11185303160002791</almaid><almaid>$$V972HAI_MAIN:21180371490002791$$O972ELLAD_ALMA21180371490002791</almaid><sourcerecordid>$$V11185303160002791$$O972ELLAD_ALMA11185303160002791</sourcerecordid><sourcerecordid>$$V21180371490002791$$O972ELLAD_ALMA21180371490002791</sourcerecordid><sourceid>$$V972ELLAD_ALMA$$O972ELLAD_ALMA11185303160002791</sourceid><sourceid>$$V972ELLAD_ALMA$$O972ELLAD_ALMA21180371490002791</sourceid><recordid>dedupmrg7704021</recordid><originalsourceid>$$V972ELLAD$$O972ELLAD_ALMA11185303160002791</originalsourceid><originalsourceid>$$V972ELLAD$$O972ELLAD_ALMA21180371490002791</originalsourceid><sourceformat>$$VMARC21$$O972ELLAD_ALMA11185303160002791</sourceformat><sourceformat>$$VMARC21$$O972ELLAD_ALMA21180371490002791</sourceformat><sourcesystem>$$VAlma$$O972ELLAD_ALMA11185303160002791</sourcesystem><sourcesystem>$$VAlma$$O972ELLAD_ALMA21180371490002791</sourcesystem><colldiscovery>$$V$$Titem$$D81173584980002791$$I$$O972ELLAD_ALMA11185303160002791</colldiscovery></control>\r\n" + 
			"<display>\r\n" + 
			"<type>book</type> \r\n" + 
			"<title>𝘉-orbits of type (3², 1²ⁿ⁻⁶) in nilradical of Lie Algebra of type 𝐶𝑛</title> \r\n" + 
			"<creator>Yazeed Maree מרעי, יזיד</creator> \r\n" + 
			"<contributor>Anna Melnikov מלניקוב, אנה; Universiṭat Ḥefah. ha-Ḥug le-matemaṭiḳah; אוניברסיטת חיפה. החוג למתמטיקה</contributor> \r\n" + 
			"<publisher>Haifa : University of Haifa</publisher> \r\n" + 
			"<creationdate>2019</creationdate> \r\n" + 
			"<format>1 online resource (iv, 47 pagves) : illustrations, PDF.</format> \r\n" + 
			"<subject>Lie groups lat; חבורות לי heb; Lie algebras lat; g Symmetric spaces lat; g Topological groups lat; Nilpotent groups lat; קבוצות נילפוטנטיות heb; g Finite groups lat; Algebras, Linear lat; אלגברה ליניארית heb; g Algebra, Universal lat; g Generalized spaces lat; g Mathematical analysis lat; Calculus of operations lat; Line geometry lat; Topology lat; Academic theses lat; תזות אקדמיות heb; Informational works g lat</subject> \r\n" + 
			"<language>eng</language> \r\n" + 
			" \r\n" + 
			"<source>$$V972ELLAD_ALMA$$O972ELLAD_ALMA11185303160002791</source><source>$$V972ELLAD_ALMA$$O972ELLAD_ALMA21180371490002791</source><availlibrary>$$I$$L$$1General Collection (14 Days)$$2(ZD270 .M29 2019 )$$Savailable$$X972HAI_MAIN$$YH1$$ZGEN40$$P1$$O972ELLAD_ALMA21180371490002791</availlibrary><availlibrary>$$I$$L$$1General Collection (Not For Loan)$$2(ZD270 .M29 2019 )$$Savailable$$X972HAI_MAIN$$YH1$$ZGEN41$$P2$$O972ELLAD_ALMA21180371490002791</availlibrary><availpnx>unavailable</availpnx></display>\r\n" + 
			"<links>\r\n" + 
			" \r\n" + 
			"<thumbnail>$$Talma_thumb2</thumbnail></links>\r\n" + 
			"<search><creatorcontrib>Yazeed  Maree  author</creatorcontrib><creatorcontrib>Yazeed  Maree</creatorcontrib><creatorcontrib>&amp;#1502;&amp;#1512;&amp;#1506;&amp;#1497;, &amp;#1497;&amp;#1494;&amp;#1497;&amp;#1491;</creatorcontrib><creatorcontrib>Maree, Y</creatorcontrib><creatorcontrib>&amp;#1502;&amp;#1512;&amp;#1506;&amp;#1497;</creatorcontrib><creatorcontrib>Yazeed Maree ; supervised by Anna Melnikov.</creatorcontrib><creatorcontrib>Anna,  Melnikov  degree supervisor.</creatorcontrib><creatorcontrib>Anna  Melnikov</creatorcontrib><creatorcontrib>&amp;#1502;&amp;#1500;&amp;#1504;&amp;#1497;&amp;#1511;&amp;#1493;&amp;#1489;, &amp;#1488;&amp;#1504;&amp;#1492;</creatorcontrib><creatorcontrib>Melnikov, A</creatorcontrib><creatorcontrib>&amp;#1502;&amp;#1500;&amp;#1504;&amp;#1497;&amp;#1511;&amp;#1493;&amp;#1489;</creatorcontrib><creatorcontrib>Universit&amp;#803;at H&amp;#803;efah. ha-&amp;#7716;ug le-matema&amp;#7789;i&amp;#7731;ah. degree granting institution.</creatorcontrib><creatorcontrib>Universit&amp;#803;at H&amp;#803;efah. ha-&amp;#7716;ug le-matema&amp;#7789;i&amp;#7731;ah</creatorcontrib><creatorcontrib>&amp;#1488;&amp;#1493;&amp;#1504;&amp;#1497;&amp;#1489;&amp;#1512;&amp;#1505;&amp;#1497;&amp;#1496;&amp;#1514; &amp;#1495;&amp;#1497;&amp;#1508;&amp;#1492;. &amp;#1492;&amp;#1495;&amp;#1493;&amp;#1490; &amp;#1500;&amp;#1502;&amp;#1514;&amp;#1502;&amp;#1496;&amp;#1497;&amp;#1511;&amp;#1492;</creatorcontrib><creatorcontrib>Universit&amp;#803;at H&amp;#803;efah. &amp;#7716;ug le-matema&amp;#7789;i&amp;#7731;ah</creatorcontrib><creatorcontrib>Universitat Hefah. ha-Hug le-matematikah (M.A.)</creatorcontrib><creatorcontrib>Universitat Hefah. ha-Hug le-matematikah (Ph.D.)</creatorcontrib><creatorcontrib>University of Haifa. Department of Mathematics (M.A.)</creatorcontrib><creatorcontrib>University of Haifa. Department of Mathematics (Ph.D.)</creatorcontrib><creatorcontrib>&amp;#1488;&amp;#1493;&amp;#1504;&amp;#1497;&amp;#1489;&amp;#1512;&amp;#1505;&amp;#1497;&amp;#1496;&amp;#1514; &amp;#1495;&amp;#1497;&amp;#1508;&amp;#1492;. &amp;#1495;&amp;#1493;&amp;#1490; &amp;#1500;&amp;#1502;&amp;#1514;&amp;#1502;&amp;#1496;&amp;#1497;&amp;#1511;&amp;#1492;</creatorcontrib><title>&amp;#55349;&amp;#56841;-orbits of type (3&amp;#178;, 1&amp;#178;&amp;#8319;&amp;#8315;&amp;#8310;) in nilradical of Lie Algebra of type &amp;#55349;&amp;#56374;&amp;#55349;&amp;#56411; /</title><subject>Algebras, Linear lat</subject><subject>Nilpotent groups lat</subject><subject>Lie groups lat</subject><subject>&amp;#1495;&amp;#1489;&amp;#1493;&amp;#1512;&amp;#1493;&amp;#1514; &amp;#1500;&amp;#1497; heb</subject><subject>Groups, Lie lat</subject><subject>Lie algebras lat</subject><subject>g Symmetric spaces lat</subject><subject>g Topological groups lat</subject><subject>&amp;#1511;&amp;#1489;&amp;#1493;&amp;#1510;&amp;#1493;&amp;#1514; &amp;#1504;&amp;#1497;&amp;#1500;&amp;#1508;&amp;#1493;&amp;#1496;&amp;#1504;&amp;#1496;&amp;#1497;&amp;#1493;&amp;#1514; heb</subject><subject>nne Groups, Nilpotent lat</subject><subject>g Finite groups lat</subject><subject>&amp;#1488;&amp;#1500;&amp;#1490;&amp;#1489;&amp;#1512;&amp;#1492; &amp;#1500;&amp;#1497;&amp;#1504;&amp;#1497;&amp;#1488;&amp;#1512;&amp;#1497;&amp;#1514; heb</subject><subject>Linear algebra lat</subject><subject>g Algebra, Universal lat</subject><subject>g Generalized spaces lat</subject><subject>g Mathematical analysis lat</subject><subject>Calculus of operations lat</subject><subject>Line geometry lat</subject><subject>Topology lat</subject><subject>Academic theses lat</subject><subject>&amp;#1514;&amp;#1494;&amp;#1493;&amp;#1514; &amp;#1488;&amp;#1511;&amp;#1491;&amp;#1502;&amp;#1497;&amp;#1493;&amp;#1514; heb</subject><subject>Academic dissertations lat</subject><subject>Bachelor&amp;apos;s theses lat</subject><subject>Diploma theses lat</subject><subject>Dissertations, Academic (PhD) lat</subject><subject>Dissertations, Academic (Master) lat</subject><subject>Doctoral dissertations lat</subject><subject>Doctoral theses lat</subject><subject>Graduate dissertations lat</subject><subject>Graduate theses lat</subject><subject>Licentiate dissertations lat</subject><subject>Licentiate theses lat</subject><subject>Master&amp;apos;s dissertations lat</subject><subject>Master&amp;apos;s theses lat</subject><subject>Ph. D. dissertations lat</subject><subject>Ph. D. theses lat</subject><subject>Senior projects lat</subject><subject>Senior theses lat</subject><subject>Theses, Academic lat</subject><subject>Undergraduate theses lat</subject><subject>&amp;#1514;&amp;#1497;&amp;#1494;&amp;#1493;&amp;#1514; &amp;#1488;&amp;#1511;&amp;#1491;&amp;#1502;&amp;#1497;&amp;#1493;&amp;#1514; heb</subject><subject>&amp;#1491;&amp;#1497;&amp;#1505;&amp;#1512;&amp;#1496;&amp;#1510;&amp;#1497;&amp;#1493;&amp;#1514; &amp;#1488;&amp;#1511;&amp;#1491;&amp;#1502;&amp;#1497;&amp;#1493;&amp;#1514; heb</subject><subject>&amp;#1506;&amp;#1489;&amp;#1493;&amp;#1491;&amp;#1493;&amp;#1514; &amp;#1490;&amp;#1502;&amp;#1512; heb</subject><subject>Informational works g lat</subject><general>[University of Haifa],</general><general>M.A. University of Haifa, Faculty of Natural Sciences, Dept. of Mathematics, 2019</general><sourceid>972ELLAD_ALMA</sourceid><recordid>972ELLAD_ALMA11185303160002791</recordid><rsrctype>book</rsrctype><creationdate>2019</creationdate><startdate>20190101</startdate><enddate>20191231</enddate><addsrcrecordid>9919479786502791</addsrcrecordid><searchscope>972ELLAD_ALMA</searchscope><searchscope>collections</searchscope><scope>972ELLAD_ALMA</scope><scope>collections</scope><alttitle>&amp;#1502;&amp;#1505;&amp;#1500;&amp;#1493;&amp;#1500;&amp;#1497; &amp;#1489;&amp;#1493;&amp;#1512;&amp;#1500; &amp;#1502;&amp;#1505;&amp;#1493;&amp;#1490; (3&amp;#178;, 1&amp;#178;&amp;#8319;&amp;#8315;&amp;#8310;) &amp;#1489;&amp;#1504;&amp;#1497;&amp;#1500;&amp;#1512;&amp;#1491;&amp;#1497;&amp;#1511;&amp;#1500; &amp;#1513;&amp;#1500; &amp;#1488;&amp;#1500;&amp;#1490;&amp;#1489;&amp;#1512;&amp;#1514; &amp;#1500;&amp;#1497; &amp;#1502;&amp;#1505;&amp;#1493;&amp;#1490; &amp;#55349;&amp;#56374;&amp;#55349;&amp;#56411; /</alttitle><alttitle>Borel orbits of type (3&amp;#178;, 1&amp;#178;&amp;#8319;&amp;#8315;&amp;#8310;) in nilradical of Lie Algebra of type &amp;#55349;&amp;#56374;&amp;#55349;&amp;#56411;.</alttitle><cdparentID>81173584980002791</cdparentID><recordid>972ELLAD_ALMA21180371490002791</recordid><addsrcrecordid>9919462873002791</addsrcrecordid></search>\r\n" + 
			"<sort>\r\n" + 
			"<title>𝘉-orbits of type (3², 1²ⁿ⁻⁶) in nilradical of Lie Algebra of type 𝐶𝑛 /</title> \r\n" + 
			"<creationdate>2019</creationdate> \r\n" + 
			"<author>Maree, Yazeed</author> \r\n" + 
			"</sort>\r\n" + 
			"<facets><language>eng</language><creationdate>2019</creationdate><topic>Lie groups lat</topic><topic>&amp;#1495;&amp;#1489;&amp;#1493;&amp;#1512;&amp;#1493;&amp;#1514; &amp;#1500;&amp;#1497; heb</topic><topic>Lie algebras lat</topic><topic>g Symmetric spaces lat</topic><topic>g Topological groups lat</topic><topic>Nilpotent groups lat</topic><topic>&amp;#1511;&amp;#1489;&amp;#1493;&amp;#1510;&amp;#1493;&amp;#1514; &amp;#1504;&amp;#1497;&amp;#1500;&amp;#1508;&amp;#1493;&amp;#1496;&amp;#1504;&amp;#1496;&amp;#1497;&amp;#1493;&amp;#1514; heb</topic><topic>g Finite groups lat</topic><topic>Algebras, Linear lat</topic><topic>&amp;#1488;&amp;#1500;&amp;#1490;&amp;#1489;&amp;#1512;&amp;#1492; &amp;#1500;&amp;#1497;&amp;#1504;&amp;#1497;&amp;#1488;&amp;#1512;&amp;#1497;&amp;#1514; heb</topic><topic>g Algebra, Universal lat</topic><topic>g Generalized spaces lat</topic><topic>g Mathematical analysis lat</topic><topic>Calculus of operations lat</topic><topic>Line geometry lat</topic><topic>Topology lat</topic><collection>&amp;#1492;&amp;#1495;&amp;#1493;&amp;#1490; &amp;#1500;&amp;#1502;&amp;#1514;&amp;#1502;&amp;#1496;&amp;#1497;&amp;#1511;&amp;#1492; - Department of Mathematics (MA)</collection><toplevel>online_resources</toplevel><prefilter>books</prefilter><rsrctype>books</rsrctype><creatorcontrib>Maree, Yazeed</creatorcontrib><creatorcontrib>&amp;#1502;&amp;#1512;&amp;#1506;&amp;#1497;, &amp;#1497;&amp;#1494;&amp;#1497;&amp;#1491;</creatorcontrib><creatorcontrib>Melnikov, Anna</creatorcontrib><creatorcontrib>&amp;#1502;&amp;#1500;&amp;#1504;&amp;#1497;&amp;#1511;&amp;#1493;&amp;#1489;, &amp;#1488;&amp;#1504;&amp;#1492;</creatorcontrib><creatorcontrib>Universit&amp;#803;at H&amp;#803;efah. ha-&amp;#7716;ug le-matema&amp;#7789;i&amp;#7731;ah</creatorcontrib><creatorcontrib>&amp;#1488;&amp;#1493;&amp;#1504;&amp;#1497;&amp;#1489;&amp;#1512;&amp;#1505;&amp;#1497;&amp;#1496;&amp;#1514; &amp;#1495;&amp;#1497;&amp;#1508;&amp;#1492;. &amp;#1492;&amp;#1495;&amp;#1493;&amp;#1490; &amp;#1500;&amp;#1502;&amp;#1514;&amp;#1502;&amp;#1496;&amp;#1497;&amp;#1511;&amp;#1492;</creatorcontrib><genre>Academic theses</genre><genre>&amp;#1514;&amp;#1494;&amp;#1493;&amp;#1514; &amp;#1488;&amp;#1511;&amp;#1491;&amp;#1502;&amp;#1497;&amp;#1493;&amp;#1514;</genre><genre>Academic dissertations</genre><genre>Bachelor&amp;apos;s theses</genre><genre>Diploma theses</genre><genre>Dissertations, Academic (PhD)</genre><genre>Dissertations, Academic (Master)</genre><genre>Doctoral dissertations</genre><genre>Doctoral theses</genre><genre>Graduate dissertations</genre><genre>Graduate theses</genre><genre>Licentiate dissertations</genre><genre>Licentiate theses</genre><genre>Master&amp;apos;s dissertations</genre><genre>Master&amp;apos;s theses</genre><genre>Ph. D. dissertations</genre><genre>Ph. D. theses</genre><genre>Senior projects</genre><genre>Senior theses</genre><genre>Theses, Academic</genre><genre>Undergraduate theses</genre><genre>&amp;#1514;&amp;#1497;&amp;#1494;&amp;#1493;&amp;#1514; &amp;#1488;&amp;#1511;&amp;#1491;&amp;#1502;&amp;#1497;&amp;#1493;&amp;#1514;</genre><genre>&amp;#1491;&amp;#1497;&amp;#1505;&amp;#1512;&amp;#1496;&amp;#1510;&amp;#1497;&amp;#1493;&amp;#1514; &amp;#1488;&amp;#1511;&amp;#1491;&amp;#1502;&amp;#1497;&amp;#1493;&amp;#1514;</genre><genre>&amp;#1506;&amp;#1489;&amp;#1493;&amp;#1491;&amp;#1493;&amp;#1514; &amp;#1490;&amp;#1502;&amp;#1512;</genre><genre>Informational works</genre><atoz>Others</atoz><newrecords>20201019_989</newrecords><toplevel>available</toplevel><newrecords>20201019_993</newrecords></facets>\r\n" + 
			"<dedup>\r\n" + 
			"<t>1</t> \r\n" + 
			"<c3>𝘉orbitsoftype3212ⁿ⁻⁶innilradicalofliealgebraoftype𝐶𝑛</c3> \r\n" + 
			"<c4>2019</c4> \r\n" + 
			"<c5>9919479786502791</c5> \r\n" + 
			"<f5>𝘉orbitsoftype3212ⁿ⁻⁶innilradicalofliealgebraoftype𝐶𝑛</f5> \r\n" + 
			"<f6>2019</f6> \r\n" + 
			"<f7>𝘉 orbits of type 32 12ⁿ⁻⁶ in nilradical of lie algebra of type 𝐶𝑛</f7> \r\n" + 
			"<f8>is</f8> \r\n" + 
			"<f9>1 online resource (iv, 47 pagves) :</f9> \r\n" + 
			"<f10>university of haifa</f10> \r\n" + 
			"<f11>maree yazeed</f11> \r\n" + 
			"<f20>9919479786502791</f20> \r\n" + 
			"</dedup>\r\n" + 
			"<frbr><t>1</t><k1>$$Kmaree yazeed$$AA</k1><k3>$$K&amp;#55349;&amp;#56841; orbits of type 32 12&amp;#8319;&amp;#8315;&amp;#8310; in nilradical of lie algebra of type &amp;#55349;&amp;#56374;&amp;#55349;&amp;#56411;$$AT</k3></frbr>\r\n" + 
			"<delivery><delcategory>$$VAlma-D$$O972ELLAD_ALMA11185303160002791</delcategory><delcategory>$$VAlma-P$$O972ELLAD_ALMA21180371490002791</delcategory></delivery>\r\n" + 
			"<ranking>\r\n" + 
			"<booster1>1</booster1> \r\n" + 
			"<booster2>1</booster2> \r\n" + 
			"</ranking>\r\n" + 
			"<addata><aulast>Maree</aulast><aulast>&amp;#1502;&amp;#1512;&amp;#1506;&amp;#1497;, &amp;#1497;&amp;#1494;&amp;#1497;&amp;#1491;</aulast><aulast>Melnikov</aulast><aulast>&amp;#1502;&amp;#1500;&amp;#1504;&amp;#1497;&amp;#1511;&amp;#1493;&amp;#1489;, &amp;#1488;&amp;#1504;&amp;#1492;</aulast><aufirst>Yazeed</aufirst><au>Maree, Yazeed</au><addau>Melnikov, Anna</addau><addau>&amp;#1502;&amp;#1500;&amp;#1504;&amp;#1497;&amp;#1511;&amp;#1493;&amp;#1489;, &amp;#1488;&amp;#1504;&amp;#1492;</addau><addau>Universit&amp;#803;at H&amp;#803;efah. ha-&amp;#7716;ug le-matema&amp;#7789;i&amp;#7731;ah</addau><addau>&amp;#1488;&amp;#1493;&amp;#1504;&amp;#1497;&amp;#1489;&amp;#1512;&amp;#1505;&amp;#1497;&amp;#1496;&amp;#1514; &amp;#1495;&amp;#1497;&amp;#1508;&amp;#1492;. &amp;#1492;&amp;#1495;&amp;#1493;&amp;#1490; &amp;#1500;&amp;#1502;&amp;#1514;&amp;#1502;&amp;#1496;&amp;#1497;&amp;#1511;&amp;#1492;</addau><btitle>&amp;#55349;&amp;#56841;-orbits of type (3&amp;#178;, 1&amp;#178;&amp;#8319;&amp;#8315;&amp;#8310;) in nilradical of Lie Algebra of type &amp;#55349;&amp;#56374;&amp;#55349;&amp;#56411;</btitle><addtitle>&amp;#1502;&amp;#1505;&amp;#1500;&amp;#1493;&amp;#1500;&amp;#1497; &amp;#1489;&amp;#1493;&amp;#1512;&amp;#1500; &amp;#1502;&amp;#1505;&amp;#1493;&amp;#1490; (3&amp;#178;, 1&amp;#178;&amp;#8319;&amp;#8315;&amp;#8310;) &amp;#1489;&amp;#1504;&amp;#1497;&amp;#1500;&amp;#1512;&amp;#1491;&amp;#1497;&amp;#1511;&amp;#1500; &amp;#1513;&amp;#1500; &amp;#1488;&amp;#1500;&amp;#1490;&amp;#1489;&amp;#1512;&amp;#1514; &amp;#1500;&amp;#1497; &amp;#1502;&amp;#1505;&amp;#1493;&amp;#1490; &amp;#55349;&amp;#56374;&amp;#55349;&amp;#56411;</addtitle><date>2019</date><risdate>2019</risdate><format>dissertation</format><genre>book</genre><ristype>BOOK</ristype><notes>Includes bibliographical references (page 47).</notes><mis1>11185303160002791</mis1><mis1>21180371490002791</mis1></addata>\r\n" + 
			"<browse>\r\n" + 
			"<callnumber>$$I$$DZD270 .M29 2019$$EZD  270            M 29   2019$$T0</callnumber></browse>\r\n" + 
			"</record>";

	
	
	private String XMLContectEncoded="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
			"<record><control><almaid>$$V972HAI_MAIN:11185303160002791$$O972ELLAD_ALMA11185303160002791</almaid><almaid>$$V972HAI_MAIN:21180371490002791$$O972ELLAD_ALMA21180371490002791</almaid><sourcerecordid>$$V11185303160002791$$O972ELLAD_ALMA11185303160002791</sourcerecordid><sourcerecordid>$$V21180371490002791$$O972ELLAD_ALMA21180371490002791</sourcerecordid><sourceid>$$V972ELLAD_ALMA$$O972ELLAD_ALMA11185303160002791</sourceid><sourceid>$$V972ELLAD_ALMA$$O972ELLAD_ALMA21180371490002791</sourceid><recordid>dedupmrg7704060</recordid><originalsourceid>$$V972ELLAD$$O972ELLAD_ALMA11185303160002791</originalsourceid><originalsourceid>$$V972ELLAD$$O972ELLAD_ALMA21180371490002791</originalsourceid><sourceformat>$$VMARC21$$O972ELLAD_ALMA11185303160002791</sourceformat><sourceformat>$$VMARC21$$O972ELLAD_ALMA21180371490002791</sourceformat><sourcesystem>$$VAlma$$O972ELLAD_ALMA11185303160002791</sourcesystem><sourcesystem>$$VAlma$$O972ELLAD_ALMA21180371490002791</sourcesystem><colldiscovery>$$V$$Titem$$D81173584980002791$$I$$O972ELLAD_ALMA11185303160002791</colldiscovery>\r\n" + 
			"</control><display><type>book</type><title>&amp;#55349;&amp;#56841;-orbits of type (3&amp;#178;, 1&amp;#178;&amp;#8319;&amp;#8315;&amp;#8310;) in nilradical of Lie Algebra of type &amp;#55349;&amp;#56374;&amp;#55349;&amp;#56411;</title><creator>Yazeed Maree &amp;#1502;&amp;#1512;&amp;#1506;&amp;#1497;, &amp;#1497;&amp;#1494;&amp;#1497;&amp;#1491;</creator><contributor>Anna Melnikov &amp;#1502;&amp;#1500;&amp;#1504;&amp;#1497;&amp;#1511;&amp;#1493;&amp;#1489;, &amp;#1488;&amp;#1504;&amp;#1492;; Universit&amp;#803;at H&amp;#803;efah. ha-&amp;#7716;ug le-matema&amp;#7789;i&amp;#7731;ah; &amp;#1488;&amp;#1493;&amp;#1504;&amp;#1497;&amp;#1489;&amp;#1512;&amp;#1505;&amp;#1497;&amp;#1496;&amp;#1514; &amp;#1495;&amp;#1497;&amp;#1508;&amp;#1492;. &amp;#1492;&amp;#1495;&amp;#1493;&amp;#1490; &amp;#1500;&amp;#1502;&amp;#1514;&amp;#1502;&amp;#1496;&amp;#1497;&amp;#1511;&amp;#1492;</contributor><publisher>Haifa : University of Haifa</publisher><creationdate>2019</creationdate><format>1 online resource (iv, 47 pagves) : illustrations, PDF.</format><subject>Lie groups lat; &amp;#1495;&amp;#1489;&amp;#1493;&amp;#1512;&amp;#1493;&amp;#1514; &amp;#1500;&amp;#1497; heb; Lie algebras lat; g Symmetric spaces lat; g Topological groups lat; Nilpotent groups lat; &amp;#1511;&amp;#1489;&amp;#1493;&amp;#1510;&amp;#1493;&amp;#1514; &amp;#1504;&amp;#1497;&amp;#1500;&amp;#1508;&amp;#1493;&amp;#1496;&amp;#1504;&amp;#1496;&amp;#1497;&amp;#1493;&amp;#1514; heb; g Finite groups lat; Algebras, Linear lat; &amp;#1488;&amp;#1500;&amp;#1490;&amp;#1489;&amp;#1512;&amp;#1492; &amp;#1500;&amp;#1497;&amp;#1504;&amp;#1497;&amp;#1488;&amp;#1512;&amp;#1497;&amp;#1514; heb; g Algebra, Universal lat; g Generalized spaces lat; g Mathematical analysis lat; Calculus of operations lat; Line geometry lat; Topology lat; Academic theses lat; &amp;#1514;&amp;#1494;&amp;#1493;&amp;#1514; &amp;#1488;&amp;#1511;&amp;#1491;&amp;#1502;&amp;#1497;&amp;#1493;&amp;#1514; heb; Informational works g lat</subject><language>eng</language><source>$$V972ELLAD_ALMA$$O972ELLAD_ALMA11185303160002791</source><source>$$V972ELLAD_ALMA$$O972ELLAD_ALMA21180371490002791</source><availlibrary>$$I$$L$$1General Collection (14 Days)$$2(ZD270 .M29 2019 )$$Savailable$$X972HAI_MAIN$$YH1$$ZGEN40$$P1$$O972ELLAD_ALMA21180371490002791</availlibrary><availlibrary>$$I$$L$$1General Collection (Not For Loan)$$2(ZD270 .M29 2019 )$$Savailable$$X972HAI_MAIN$$YH1$$ZGEN41$$P2$$O972ELLAD_ALMA21180371490002791</availlibrary><availpnx>unavailable</availpnx>\r\n" + 
			"</display><links><thumbnail>$$Talma_thumb2</thumbnail>\r\n" + 
			"</links><search><creatorcontrib>Yazeed  Maree  author</creatorcontrib><creatorcontrib>Yazeed  Maree</creatorcontrib><creatorcontrib>&amp;amp;#1502;&amp;amp;#1512;&amp;amp;#1506;&amp;amp;#1497;, &amp;amp;#1497;&amp;amp;#1494;&amp;amp;#1497;&amp;amp;#1491;</creatorcontrib><creatorcontrib>Maree, Y</creatorcontrib><creatorcontrib>&amp;amp;#1502;&amp;amp;#1512;&amp;amp;#1506;&amp;amp;#1497;</creatorcontrib><creatorcontrib>Yazeed Maree ; supervised by Anna Melnikov.</creatorcontrib><creatorcontrib>Anna,  Melnikov  degree supervisor.</creatorcontrib><creatorcontrib>Anna  Melnikov</creatorcontrib><creatorcontrib>&amp;amp;#1502;&amp;amp;#1500;&amp;amp;#1504;&amp;amp;#1497;&amp;amp;#1511;&amp;amp;#1493;&amp;amp;#1489;, &amp;amp;#1488;&amp;amp;#1504;&amp;amp;#1492;</creatorcontrib><creatorcontrib>Melnikov, A</creatorcontrib><creatorcontrib>&amp;amp;#1502;&amp;amp;#1500;&amp;amp;#1504;&amp;amp;#1497;&amp;amp;#1511;&amp;amp;#1493;&amp;amp;#1489;</creatorcontrib><creatorcontrib>Universit&amp;amp;#803;at H&amp;amp;#803;efah. ha-&amp;amp;#7716;ug le-matema&amp;amp;#7789;i&amp;amp;#7731;ah. degree granting institution.</creatorcontrib><creatorcontrib>Universit&amp;amp;#803;at H&amp;amp;#803;efah. ha-&amp;amp;#7716;ug le-matema&amp;amp;#7789;i&amp;amp;#7731;ah</creatorcontrib><creatorcontrib>&amp;amp;#1488;&amp;amp;#1493;&amp;amp;#1504;&amp;amp;#1497;&amp;amp;#1489;&amp;amp;#1512;&amp;amp;#1505;&amp;amp;#1497;&amp;amp;#1496;&amp;amp;#1514; &amp;amp;#1495;&amp;amp;#1497;&amp;amp;#1508;&amp;amp;#1492;. &amp;amp;#1492;&amp;amp;#1495;&amp;amp;#1493;&amp;amp;#1490; &amp;amp;#1500;&amp;amp;#1502;&amp;amp;#1514;&amp;amp;#1502;&amp;amp;#1496;&amp;amp;#1497;&amp;amp;#1511;&amp;amp;#1492;</creatorcontrib><creatorcontrib>Universit&amp;amp;#803;at H&amp;amp;#803;efah. &amp;amp;#7716;ug le-matema&amp;amp;#7789;i&amp;amp;#7731;ah</creatorcontrib><creatorcontrib>Universitat Hefah. ha-Hug le-matematikah (M.A.)</creatorcontrib><creatorcontrib>Universitat Hefah. ha-Hug le-matematikah (Ph.D.)</creatorcontrib><creatorcontrib>University of Haifa. Department of Mathematics (M.A.)</creatorcontrib><creatorcontrib>University of Haifa. Department of Mathematics (Ph.D.)</creatorcontrib><creatorcontrib>&amp;amp;#1488;&amp;amp;#1493;&amp;amp;#1504;&amp;amp;#1497;&amp;amp;#1489;&amp;amp;#1512;&amp;amp;#1505;&amp;amp;#1497;&amp;amp;#1496;&amp;amp;#1514; &amp;amp;#1495;&amp;amp;#1497;&amp;amp;#1508;&amp;amp;#1492;. &amp;amp;#1495;&amp;amp;#1493;&amp;amp;#1490; &amp;amp;#1500;&amp;amp;#1502;&amp;amp;#1514;&amp;amp;#1502;&amp;amp;#1496;&amp;amp;#1497;&amp;amp;#1511;&amp;amp;#1492;</creatorcontrib><title>&amp;amp;#55349;&amp;amp;#56841;-orbits of type (3&amp;amp;#178;, 1&amp;amp;#178;&amp;amp;#8319;&amp;amp;#8315;&amp;amp;#8310;) in nilradical of Lie Algebra of type &amp;amp;#55349;&amp;amp;#56374;&amp;amp;#55349;&amp;amp;#56411; /</title><subject>Algebras, Linear lat</subject><subject>Nilpotent groups lat</subject><subject>Lie groups lat</subject><subject>&amp;amp;#1495;&amp;amp;#1489;&amp;amp;#1493;&amp;amp;#1512;&amp;amp;#1493;&amp;amp;#1514; &amp;amp;#1500;&amp;amp;#1497; heb</subject><subject>Groups, Lie lat</subject><subject>Lie algebras lat</subject><subject>g Symmetric spaces lat</subject><subject>g Topological groups lat</subject><subject>&amp;amp;#1511;&amp;amp;#1489;&amp;amp;#1493;&amp;amp;#1510;&amp;amp;#1493;&amp;amp;#1514; &amp;amp;#1504;&amp;amp;#1497;&amp;amp;#1500;&amp;amp;#1508;&amp;amp;#1493;&amp;amp;#1496;&amp;amp;#1504;&amp;amp;#1496;&amp;amp;#1497;&amp;amp;#1493;&amp;amp;#1514; heb</subject><subject>nne Groups, Nilpotent lat</subject><subject>g Finite groups lat</subject><subject>&amp;amp;#1488;&amp;amp;#1500;&amp;amp;#1490;&amp;amp;#1489;&amp;amp;#1512;&amp;amp;#1492; &amp;amp;#1500;&amp;amp;#1497;&amp;amp;#1504;&amp;amp;#1497;&amp;amp;#1488;&amp;amp;#1512;&amp;amp;#1497;&amp;amp;#1514; heb</subject><subject>Linear algebra lat</subject><subject>g Algebra, Universal lat</subject><subject>g Generalized spaces lat</subject><subject>g Mathematical analysis lat</subject><subject>Calculus of operations lat</subject><subject>Line geometry lat</subject><subject>Topology lat</subject><subject>Academic theses lat</subject><subject>&amp;amp;#1514;&amp;amp;#1494;&amp;amp;#1493;&amp;amp;#1514; &amp;amp;#1488;&amp;amp;#1511;&amp;amp;#1491;&amp;amp;#1502;&amp;amp;#1497;&amp;amp;#1493;&amp;amp;#1514; heb</subject><subject>Academic dissertations lat</subject><subject>Bachelor&amp;amp;apos;s theses lat</subject><subject>Diploma theses lat</subject><subject>Dissertations, Academic (PhD) lat</subject><subject>Dissertations, Academic (Master) lat</subject><subject>Doctoral dissertations lat</subject><subject>Doctoral theses lat</subject><subject>Graduate dissertations lat</subject><subject>Graduate theses lat</subject><subject>Licentiate dissertations lat</subject><subject>Licentiate theses lat</subject><subject>Master&amp;amp;apos;s dissertations lat</subject><subject>Master&amp;amp;apos;s theses lat</subject><subject>Ph. D. dissertations lat</subject><subject>Ph. D. theses lat</subject><subject>Senior projects lat</subject><subject>Senior theses lat</subject><subject>Theses, Academic lat</subject><subject>Undergraduate theses lat</subject><subject>&amp;amp;#1514;&amp;amp;#1497;&amp;amp;#1494;&amp;amp;#1493;&amp;amp;#1514; &amp;amp;#1488;&amp;amp;#1511;&amp;amp;#1491;&amp;amp;#1502;&amp;amp;#1497;&amp;amp;#1493;&amp;amp;#1514; heb</subject><subject>&amp;amp;#1491;&amp;amp;#1497;&amp;amp;#1505;&amp;amp;#1512;&amp;amp;#1496;&amp;amp;#1510;&amp;amp;#1497;&amp;amp;#1493;&amp;amp;#1514; &amp;amp;#1488;&amp;amp;#1511;&amp;amp;#1491;&amp;amp;#1502;&amp;amp;#1497;&amp;amp;#1493;&amp;amp;#1514; heb</subject><subject>&amp;amp;#1506;&amp;amp;#1489;&amp;amp;#1493;&amp;amp;#1491;&amp;amp;#1493;&amp;amp;#1514; &amp;amp;#1490;&amp;amp;#1502;&amp;amp;#1512; heb</subject><subject>Informational works g lat</subject><general>[University of Haifa],</general><general>M.A. University of Haifa, Faculty of Natural Sciences, Dept. of Mathematics, 2019</general><sourceid>972ELLAD_ALMA</sourceid><recordid>972ELLAD_ALMA11185303160002791</recordid><rsrctype>book</rsrctype><creationdate>2019</creationdate><startdate>20190101</startdate><enddate>20191231</enddate><addsrcrecordid>9919479786502791</addsrcrecordid><searchscope>972ELLAD_ALMA</searchscope><searchscope>collections</searchscope><scope>972ELLAD_ALMA</scope><scope>collections</scope><alttitle>&amp;amp;#1502;&amp;amp;#1505;&amp;amp;#1500;&amp;amp;#1493;&amp;amp;#1500;&amp;amp;#1497; &amp;amp;#1489;&amp;amp;#1493;&amp;amp;#1512;&amp;amp;#1500; &amp;amp;#1502;&amp;amp;#1505;&amp;amp;#1493;&amp;amp;#1490; (3&amp;amp;#178;, 1&amp;amp;#178;&amp;amp;#8319;&amp;amp;#8315;&amp;amp;#8310;) &amp;amp;#1489;&amp;amp;#1504;&amp;amp;#1497;&amp;amp;#1500;&amp;amp;#1512;&amp;amp;#1491;&amp;amp;#1497;&amp;amp;#1511;&amp;amp;#1500; &amp;amp;#1513;&amp;amp;#1500; &amp;amp;#1488;&amp;amp;#1500;&amp;amp;#1490;&amp;amp;#1489;&amp;amp;#1512;&amp;amp;#1514; &amp;amp;#1500;&amp;amp;#1497; &amp;amp;#1502;&amp;amp;#1505;&amp;amp;#1493;&amp;amp;#1490; &amp;amp;#55349;&amp;amp;#56374;&amp;amp;#55349;&amp;amp;#56411; /</alttitle><alttitle>Borel orbits of type (3&amp;amp;#178;, 1&amp;amp;#178;&amp;amp;#8319;&amp;amp;#8315;&amp;amp;#8310;) in nilradical of Lie Algebra of type &amp;amp;#55349;&amp;amp;#56374;&amp;amp;#55349;&amp;amp;#56411;.</alttitle><cdparentID>81173584980002791</cdparentID><recordid>972ELLAD_ALMA21180371490002791</recordid><addsrcrecordid>9919462873002791</addsrcrecordid></search><sort><title>&amp;#55349;&amp;#56841;-orbits of type (3&amp;#178;, 1&amp;#178;&amp;#8319;&amp;#8315;&amp;#8310;) in nilradical of Lie Algebra of type &amp;#55349;&amp;#56374;&amp;#55349;&amp;#56411; /</title><creationdate>2019</creationdate><author>Maree, Yazeed</author>\r\n" + 
			"</sort><facets><language>eng</language><creationdate>2019</creationdate><topic>Lie groups lat</topic><topic>&amp;amp;#1495;&amp;amp;#1489;&amp;amp;#1493;&amp;amp;#1512;&amp;amp;#1493;&amp;amp;#1514; &amp;amp;#1500;&amp;amp;#1497; heb</topic><topic>Lie algebras lat</topic><topic>g Symmetric spaces lat</topic><topic>g Topological groups lat</topic><topic>Nilpotent groups lat</topic><topic>&amp;amp;#1511;&amp;amp;#1489;&amp;amp;#1493;&amp;amp;#1510;&amp;amp;#1493;&amp;amp;#1514; &amp;amp;#1504;&amp;amp;#1497;&amp;amp;#1500;&amp;amp;#1508;&amp;amp;#1493;&amp;amp;#1496;&amp;amp;#1504;&amp;amp;#1496;&amp;amp;#1497;&amp;amp;#1493;&amp;amp;#1514; heb</topic><topic>g Finite groups lat</topic><topic>Algebras, Linear lat</topic><topic>&amp;amp;#1488;&amp;amp;#1500;&amp;amp;#1490;&amp;amp;#1489;&amp;amp;#1512;&amp;amp;#1492; &amp;amp;#1500;&amp;amp;#1497;&amp;amp;#1504;&amp;amp;#1497;&amp;amp;#1488;&amp;amp;#1512;&amp;amp;#1497;&amp;amp;#1514; heb</topic><topic>g Algebra, Universal lat</topic><topic>g Generalized spaces lat</topic><topic>g Mathematical analysis lat</topic><topic>Calculus of operations lat</topic><topic>Line geometry lat</topic><topic>Topology lat</topic><collection>&amp;amp;#1492;&amp;amp;#1495;&amp;amp;#1493;&amp;amp;#1490; &amp;amp;#1500;&amp;amp;#1502;&amp;amp;#1514;&amp;amp;#1502;&amp;amp;#1496;&amp;amp;#1497;&amp;amp;#1511;&amp;amp;#1492; - Department of Mathematics (MA)</collection><toplevel>online_resources</toplevel><prefilter>books</prefilter><rsrctype>books</rsrctype><creatorcontrib>Maree, Yazeed</creatorcontrib><creatorcontrib>&amp;amp;#1502;&amp;amp;#1512;&amp;amp;#1506;&amp;amp;#1497;, &amp;amp;#1497;&amp;amp;#1494;&amp;amp;#1497;&amp;amp;#1491;</creatorcontrib><creatorcontrib>Melnikov, Anna</creatorcontrib><creatorcontrib>&amp;amp;#1502;&amp;amp;#1500;&amp;amp;#1504;&amp;amp;#1497;&amp;amp;#1511;&amp;amp;#1493;&amp;amp;#1489;, &amp;amp;#1488;&amp;amp;#1504;&amp;amp;#1492;</creatorcontrib><creatorcontrib>Universit&amp;amp;#803;at H&amp;amp;#803;efah. ha-&amp;amp;#7716;ug le-matema&amp;amp;#7789;i&amp;amp;#7731;ah</creatorcontrib><creatorcontrib>&amp;amp;#1488;&amp;amp;#1493;&amp;amp;#1504;&amp;amp;#1497;&amp;amp;#1489;&amp;amp;#1512;&amp;amp;#1505;&amp;amp;#1497;&amp;amp;#1496;&amp;amp;#1514; &amp;amp;#1495;&amp;amp;#1497;&amp;amp;#1508;&amp;amp;#1492;. &amp;amp;#1492;&amp;amp;#1495;&amp;amp;#1493;&amp;amp;#1490; &amp;amp;#1500;&amp;amp;#1502;&amp;amp;#1514;&amp;amp;#1502;&amp;amp;#1496;&amp;amp;#1497;&amp;amp;#1511;&amp;amp;#1492;</creatorcontrib><genre>Academic theses</genre><genre>&amp;amp;#1514;&amp;amp;#1494;&amp;amp;#1493;&amp;amp;#1514; &amp;amp;#1488;&amp;amp;#1511;&amp;amp;#1491;&amp;amp;#1502;&amp;amp;#1497;&amp;amp;#1493;&amp;amp;#1514;</genre><genre>Academic dissertations</genre><genre>Bachelor&amp;amp;apos;s theses</genre><genre>Diploma theses</genre><genre>Dissertations, Academic (PhD)</genre><genre>Dissertations, Academic (Master)</genre><genre>Doctoral dissertations</genre><genre>Doctoral theses</genre><genre>Graduate dissertations</genre><genre>Graduate theses</genre><genre>Licentiate dissertations</genre><genre>Licentiate theses</genre><genre>Master&amp;amp;apos;s dissertations</genre><genre>Master&amp;amp;apos;s theses</genre><genre>Ph. D. dissertations</genre><genre>Ph. D. theses</genre><genre>Senior projects</genre><genre>Senior theses</genre><genre>Theses, Academic</genre><genre>Undergraduate theses</genre><genre>&amp;amp;#1514;&amp;amp;#1497;&amp;amp;#1494;&amp;amp;#1493;&amp;amp;#1514; &amp;amp;#1488;&amp;amp;#1511;&amp;amp;#1491;&amp;amp;#1502;&amp;amp;#1497;&amp;amp;#1493;&amp;amp;#1514;</genre><genre>&amp;amp;#1491;&amp;amp;#1497;&amp;amp;#1505;&amp;amp;#1512;&amp;amp;#1496;&amp;amp;#1510;&amp;amp;#1497;&amp;amp;#1493;&amp;amp;#1514; &amp;amp;#1488;&amp;amp;#1511;&amp;amp;#1491;&amp;amp;#1502;&amp;amp;#1497;&amp;amp;#1493;&amp;amp;#1514;</genre><genre>&amp;amp;#1506;&amp;amp;#1489;&amp;amp;#1493;&amp;amp;#1491;&amp;amp;#1493;&amp;amp;#1514; &amp;amp;#1490;&amp;amp;#1502;&amp;amp;#1512;</genre><genre>Informational works</genre><atoz>Others</atoz><newrecords>20201019_559</newrecords><toplevel>available</toplevel><newrecords>20201019_567</newrecords></facets><dedup><t>1</t><c3>&amp;#55349;&amp;#56841;orbitsoftype3212&amp;#8319;&amp;#8315;&amp;#8310;innilradicalofliealgebraoftype&amp;#55349;&amp;#56374;&amp;#55349;&amp;#56411;</c3><c4>2019</c4><c5>9919479786502791</c5><f5>&amp;#55349;&amp;#56841;orbitsoftype3212&amp;#8319;&amp;#8315;&amp;#8310;innilradicalofliealgebraoftype&amp;#55349;&amp;#56374;&amp;#55349;&amp;#56411;</f5><f6>2019</f6><f7>&amp;#55349;&amp;#56841; orbits of type 32 12&amp;#8319;&amp;#8315;&amp;#8310; in nilradical of lie algebra of type &amp;#55349;&amp;#56374;&amp;#55349;&amp;#56411;</f7><f8>is</f8><f9>1 online resource (iv, 47 pagves) :</f9><f10>university of haifa</f10><f11>maree yazeed</f11><f20>9919479786502791</f20>\r\n" + 
			"</dedup><frbr><t>1</t><k1>$$Kmaree yazeed$$AA</k1><k3>$$K&amp;amp;#55349;&amp;amp;#56841; orbits of type 32 12&amp;amp;#8319;&amp;amp;#8315;&amp;amp;#8310; in nilradical of lie algebra of type &amp;amp;#55349;&amp;amp;#56374;&amp;amp;#55349;&amp;amp;#56411;$$AT</k3></frbr><delivery><delcategory>$$VAlma-D$$O972ELLAD_ALMA11185303160002791</delcategory><delcategory>$$VAlma-P$$O972ELLAD_ALMA21180371490002791</delcategory></delivery><ranking><booster1>1</booster1><booster2>1</booster2>\r\n" + 
			"</ranking><addata><aulast>Maree</aulast><aulast>&amp;amp;#1502;&amp;amp;#1512;&amp;amp;#1506;&amp;amp;#1497;, &amp;amp;#1497;&amp;amp;#1494;&amp;amp;#1497;&amp;amp;#1491;</aulast><aulast>Melnikov</aulast><aulast>&amp;amp;#1502;&amp;amp;#1500;&amp;amp;#1504;&amp;amp;#1497;&amp;amp;#1511;&amp;amp;#1493;&amp;amp;#1489;, &amp;amp;#1488;&amp;amp;#1504;&amp;amp;#1492;</aulast><aufirst>Yazeed</aufirst><au>Maree, Yazeed</au><addau>Melnikov, Anna</addau><addau>&amp;amp;#1502;&amp;amp;#1500;&amp;amp;#1504;&amp;amp;#1497;&amp;amp;#1511;&amp;amp;#1493;&amp;amp;#1489;, &amp;amp;#1488;&amp;amp;#1504;&amp;amp;#1492;</addau><addau>Universit&amp;amp;#803;at H&amp;amp;#803;efah. ha-&amp;amp;#7716;ug le-matema&amp;amp;#7789;i&amp;amp;#7731;ah</addau><addau>&amp;amp;#1488;&amp;amp;#1493;&amp;amp;#1504;&amp;amp;#1497;&amp;amp;#1489;&amp;amp;#1512;&amp;amp;#1505;&amp;amp;#1497;&amp;amp;#1496;&amp;amp;#1514; &amp;amp;#1495;&amp;amp;#1497;&amp;amp;#1508;&amp;amp;#1492;. &amp;amp;#1492;&amp;amp;#1495;&amp;amp;#1493;&amp;amp;#1490; &amp;amp;#1500;&amp;amp;#1502;&amp;amp;#1514;&amp;amp;#1502;&amp;amp;#1496;&amp;amp;#1497;&amp;amp;#1511;&amp;amp;#1492;</addau><btitle>&amp;amp;#55349;&amp;amp;#56841;-orbits of type (3&amp;amp;#178;, 1&amp;amp;#178;&amp;amp;#8319;&amp;amp;#8315;&amp;amp;#8310;) in nilradical of Lie Algebra of type &amp;amp;#55349;&amp;amp;#56374;&amp;amp;#55349;&amp;amp;#56411;</btitle><addtitle>&amp;amp;#1502;&amp;amp;#1505;&amp;amp;#1500;&amp;amp;#1493;&amp;amp;#1500;&amp;amp;#1497; &amp;amp;#1489;&amp;amp;#1493;&amp;amp;#1512;&amp;amp;#1500; &amp;amp;#1502;&amp;amp;#1505;&amp;amp;#1493;&amp;amp;#1490; (3&amp;amp;#178;, 1&amp;amp;#178;&amp;amp;#8319;&amp;amp;#8315;&amp;amp;#8310;) &amp;amp;#1489;&amp;amp;#1504;&amp;amp;#1497;&amp;amp;#1500;&amp;amp;#1512;&amp;amp;#1491;&amp;amp;#1497;&amp;amp;#1511;&amp;amp;#1500; &amp;amp;#1513;&amp;amp;#1500; &amp;amp;#1488;&amp;amp;#1500;&amp;amp;#1490;&amp;amp;#1489;&amp;amp;#1512;&amp;amp;#1514; &amp;amp;#1500;&amp;amp;#1497; &amp;amp;#1502;&amp;amp;#1505;&amp;amp;#1493;&amp;amp;#1490; &amp;amp;#55349;&amp;amp;#56374;&amp;amp;#55349;&amp;amp;#56411;</addtitle><date>2019</date><risdate>2019</risdate><format>dissertation</format><genre>book</genre><ristype>BOOK</ristype><notes>Includes bibliographical references (page 47).</notes><mis1>11185303160002791</mis1><mis1>21180371490002791</mis1></addata><browse><callnumber>$$I$$DZD270 .M29 2019$$EZD  270            M 29   2019$$T0</callnumber>\r\n" + 
			"</browse>\r\n" + 
			"</record>";

	
	
	
	private static final String XMLEncodReplace="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
			"<record><control><almaid>$$V972HAI_MAIN:11185303160002791$$O972ELLAD_ALMA11185303160002791</almaid><almaid>$$V972HAI_MAIN:21180371490002791$$O972ELLAD_ALMA21180371490002791</almaid><sourcerecordid>$$V11185303160002791$$O972ELLAD_ALMA11185303160002791</sourcerecordid><sourcerecordid>$$V21180371490002791$$O972ELLAD_ALMA21180371490002791</sourcerecordid><sourceid>$$V972ELLAD_ALMA$$O972ELLAD_ALMA11185303160002791</sourceid><sourceid>$$V972ELLAD_ALMA$$O972ELLAD_ALMA21180371490002791</sourceid><recordid>dedupmrg7704060</recordid><originalsourceid>$$V972ELLAD$$O972ELLAD_ALMA11185303160002791</originalsourceid><originalsourceid>$$V972ELLAD$$O972ELLAD_ALMA21180371490002791</originalsourceid><sourceformat>$$VMARC21$$O972ELLAD_ALMA11185303160002791</sourceformat><sourceformat>$$VMARC21$$O972ELLAD_ALMA21180371490002791</sourceformat><sourcesystem>$$VAlma$$O972ELLAD_ALMA11185303160002791</sourcesystem><sourcesystem>$$VAlma$$O972ELLAD_ALMA21180371490002791</sourcesystem><colldiscovery>$$V$$Titem$$D81173584980002791$$I$$O972ELLAD_ALMA11185303160002791</colldiscovery>\r\n" + 
			"</control><display><type>book</type><title>&#55349;&#56841;-orbits of type (3&#178;, 1&#178;&#8319;&#8315;&#8310;) in nilradical of Lie Algebra of type &#55349;&#56374;&#55349;&#56411;</title><creator>Yazeed Maree &#1502;&#1512;&#1506;&#1497;, &#1497;&#1494;&#1497;&#1491;</creator><contributor>Anna Melnikov &#1502;&#1500;&#1504;&#1497;&#1511;&#1493;&#1489;, &#1488;&#1504;&#1492;; Universit&#803;at H&#803;efah. ha-&#7716;ug le-matema&#7789;i&#7731;ah; &#1488;&#1493;&#1504;&#1497;&#1489;&#1512;&#1505;&#1497;&#1496;&#1514; &#1495;&#1497;&#1508;&#1492;. &#1492;&#1495;&#1493;&#1490; &#1500;&#1502;&#1514;&#1502;&#1496;&#1497;&#1511;&#1492;</contributor><publisher>Haifa : University of Haifa</publisher><creationdate>2019</creationdate><format>1 online resource (iv, 47 pagves) : illustrations, PDF.</format><subject>Lie groups lat; &#1495;&#1489;&#1493;&#1512;&#1493;&#1514; &#1500;&#1497; heb; Lie algebras lat; g Symmetric spaces lat; g Topological groups lat; Nilpotent groups lat; &#1511;&#1489;&#1493;&#1510;&#1493;&#1514; &#1504;&#1497;&#1500;&#1508;&#1493;&#1496;&#1504;&#1496;&#1497;&#1493;&#1514; heb; g Finite groups lat; Algebras, Linear lat; &#1488;&#1500;&#1490;&#1489;&#1512;&#1492; &#1500;&#1497;&#1504;&#1497;&#1488;&#1512;&#1497;&#1514; heb; g Algebra, Universal lat; g Generalized spaces lat; g Mathematical analysis lat; Calculus of operations lat; Line geometry lat; Topology lat; Academic theses lat; &#1514;&#1494;&#1493;&#1514; &#1488;&#1511;&#1491;&#1502;&#1497;&#1493;&#1514; heb; Informational works g lat</subject><language>eng</language><source>$$V972ELLAD_ALMA$$O972ELLAD_ALMA11185303160002791</source><source>$$V972ELLAD_ALMA$$O972ELLAD_ALMA21180371490002791</source><availlibrary>$$I$$L$$1General Collection (14 Days)$$2(ZD270 .M29 2019 )$$Savailable$$X972HAI_MAIN$$YH1$$ZGEN40$$P1$$O972ELLAD_ALMA21180371490002791</availlibrary><availlibrary>$$I$$L$$1General Collection (Not For Loan)$$2(ZD270 .M29 2019 )$$Savailable$$X972HAI_MAIN$$YH1$$ZGEN41$$P2$$O972ELLAD_ALMA21180371490002791</availlibrary><availpnx>unavailable</availpnx>\r\n" + 
			"</display><links><thumbnail>$$Talma_thumb2</thumbnail>\r\n" + 
			"</links><search><creatorcontrib>Yazeed  Maree  author</creatorcontrib><creatorcontrib>Yazeed  Maree</creatorcontrib><creatorcontrib>&amp;&#1502;&amp;&#1512;&amp;&#1506;&amp;&#1497;, &amp;&#1497;&amp;&#1494;&amp;&#1497;&amp;&#1491;</creatorcontrib><creatorcontrib>Maree, Y</creatorcontrib><creatorcontrib>&amp;&#1502;&amp;&#1512;&amp;&#1506;&amp;&#1497;</creatorcontrib><creatorcontrib>Yazeed Maree ; supervised by Anna Melnikov.</creatorcontrib><creatorcontrib>Anna,  Melnikov  degree supervisor.</creatorcontrib><creatorcontrib>Anna  Melnikov</creatorcontrib><creatorcontrib>&amp;&#1502;&amp;&#1500;&amp;&#1504;&amp;&#1497;&amp;&#1511;&amp;&#1493;&amp;&#1489;, &amp;&#1488;&amp;&#1504;&amp;&#1492;</creatorcontrib><creatorcontrib>Melnikov, A</creatorcontrib><creatorcontrib>&amp;&#1502;&amp;&#1500;&amp;&#1504;&amp;&#1497;&amp;&#1511;&amp;&#1493;&amp;&#1489;</creatorcontrib><creatorcontrib>Universit&amp;&#803;at H&amp;&#803;efah. ha-&amp;&#7716;ug le-matema&amp;&#7789;i&amp;&#7731;ah. degree granting institution.</creatorcontrib><creatorcontrib>Universit&amp;&#803;at H&amp;&#803;efah. ha-&amp;&#7716;ug le-matema&amp;&#7789;i&amp;&#7731;ah</creatorcontrib><creatorcontrib>&amp;&#1488;&amp;&#1493;&amp;&#1504;&amp;&#1497;&amp;&#1489;&amp;&#1512;&amp;&#1505;&amp;&#1497;&amp;&#1496;&amp;&#1514; &amp;&#1495;&amp;&#1497;&amp;&#1508;&amp;&#1492;. &amp;&#1492;&amp;&#1495;&amp;&#1493;&amp;&#1490; &amp;&#1500;&amp;&#1502;&amp;&#1514;&amp;&#1502;&amp;&#1496;&amp;&#1497;&amp;&#1511;&amp;&#1492;</creatorcontrib><creatorcontrib>Universit&amp;&#803;at H&amp;&#803;efah. &amp;&#7716;ug le-matema&amp;&#7789;i&amp;&#7731;ah</creatorcontrib><creatorcontrib>Universitat Hefah. ha-Hug le-matematikah (M.A.)</creatorcontrib><creatorcontrib>Universitat Hefah. ha-Hug le-matematikah (Ph.D.)</creatorcontrib><creatorcontrib>University of Haifa. Department of Mathematics (M.A.)</creatorcontrib><creatorcontrib>University of Haifa. Department of Mathematics (Ph.D.)</creatorcontrib><creatorcontrib>&amp;&#1488;&amp;&#1493;&amp;&#1504;&amp;&#1497;&amp;&#1489;&amp;&#1512;&amp;&#1505;&amp;&#1497;&amp;&#1496;&amp;&#1514; &amp;&#1495;&amp;&#1497;&amp;&#1508;&amp;&#1492;. &amp;&#1495;&amp;&#1493;&amp;&#1490; &amp;&#1500;&amp;&#1502;&amp;&#1514;&amp;&#1502;&amp;&#1496;&amp;&#1497;&amp;&#1511;&amp;&#1492;</creatorcontrib><title>&amp;&#55349;&amp;&#56841;-orbits of type (3&amp;&#178;, 1&amp;&#178;&amp;&#8319;&amp;&#8315;&amp;&#8310;) in nilradical of Lie Algebra of type &amp;&#55349;&amp;&#56374;&amp;&#55349;&amp;&#56411; /</title><subject>Algebras, Linear lat</subject><subject>Nilpotent groups lat</subject><subject>Lie groups lat</subject><subject>&amp;&#1495;&amp;&#1489;&amp;&#1493;&amp;&#1512;&amp;&#1493;&amp;&#1514; &amp;&#1500;&amp;&#1497; heb</subject><subject>Groups, Lie lat</subject><subject>Lie algebras lat</subject><subject>g Symmetric spaces lat</subject><subject>g Topological groups lat</subject><subject>&amp;&#1511;&amp;&#1489;&amp;&#1493;&amp;&#1510;&amp;&#1493;&amp;&#1514; &amp;&#1504;&amp;&#1497;&amp;&#1500;&amp;&#1508;&amp;&#1493;&amp;&#1496;&amp;&#1504;&amp;&#1496;&amp;&#1497;&amp;&#1493;&amp;&#1514; heb</subject><subject>nne Groups, Nilpotent lat</subject><subject>g Finite groups lat</subject><subject>&amp;&#1488;&amp;&#1500;&amp;&#1490;&amp;&#1489;&amp;&#1512;&amp;&#1492; &amp;&#1500;&amp;&#1497;&amp;&#1504;&amp;&#1497;&amp;&#1488;&amp;&#1512;&amp;&#1497;&amp;&#1514; heb</subject><subject>Linear algebra lat</subject><subject>g Algebra, Universal lat</subject><subject>g Generalized spaces lat</subject><subject>g Mathematical analysis lat</subject><subject>Calculus of operations lat</subject><subject>Line geometry lat</subject><subject>Topology lat</subject><subject>Academic theses lat</subject><subject>&amp;&#1514;&amp;&#1494;&amp;&#1493;&amp;&#1514; &amp;&#1488;&amp;&#1511;&amp;&#1491;&amp;&#1502;&amp;&#1497;&amp;&#1493;&amp;&#1514; heb</subject><subject>Academic dissertations lat</subject><subject>Bachelor&amp;amp;apos;s theses lat</subject><subject>Diploma theses lat</subject><subject>Dissertations, Academic (PhD) lat</subject><subject>Dissertations, Academic (Master) lat</subject><subject>Doctoral dissertations lat</subject><subject>Doctoral theses lat</subject><subject>Graduate dissertations lat</subject><subject>Graduate theses lat</subject><subject>Licentiate dissertations lat</subject><subject>Licentiate theses lat</subject><subject>Master&amp;amp;apos;s dissertations lat</subject><subject>Master&amp;amp;apos;s theses lat</subject><subject>Ph. D. dissertations lat</subject><subject>Ph. D. theses lat</subject><subject>Senior projects lat</subject><subject>Senior theses lat</subject><subject>Theses, Academic lat</subject><subject>Undergraduate theses lat</subject><subject>&amp;&#1514;&amp;&#1497;&amp;&#1494;&amp;&#1493;&amp;&#1514; &amp;&#1488;&amp;&#1511;&amp;&#1491;&amp;&#1502;&amp;&#1497;&amp;&#1493;&amp;&#1514; heb</subject><subject>&amp;&#1491;&amp;&#1497;&amp;&#1505;&amp;&#1512;&amp;&#1496;&amp;&#1510;&amp;&#1497;&amp;&#1493;&amp;&#1514; &amp;&#1488;&amp;&#1511;&amp;&#1491;&amp;&#1502;&amp;&#1497;&amp;&#1493;&amp;&#1514; heb</subject><subject>&amp;&#1506;&amp;&#1489;&amp;&#1493;&amp;&#1491;&amp;&#1493;&amp;&#1514; &amp;&#1490;&amp;&#1502;&amp;&#1512; heb</subject><subject>Informational works g lat</subject><general>[University of Haifa],</general><general>M.A. University of Haifa, Faculty of Natural Sciences, Dept. of Mathematics, 2019</general><sourceid>972ELLAD_ALMA</sourceid><recordid>972ELLAD_ALMA11185303160002791</recordid><rsrctype>book</rsrctype><creationdate>2019</creationdate><startdate>20190101</startdate><enddate>20191231</enddate><addsrcrecordid>9919479786502791</addsrcrecordid><searchscope>972ELLAD_ALMA</searchscope><searchscope>collections</searchscope><scope>972ELLAD_ALMA</scope><scope>collections</scope><alttitle>&amp;&#1502;&amp;&#1505;&amp;&#1500;&amp;&#1493;&amp;&#1500;&amp;&#1497; &amp;&#1489;&amp;&#1493;&amp;&#1512;&amp;&#1500; &amp;&#1502;&amp;&#1505;&amp;&#1493;&amp;&#1490; (3&amp;&#178;, 1&amp;&#178;&amp;&#8319;&amp;&#8315;&amp;&#8310;) &amp;&#1489;&amp;&#1504;&amp;&#1497;&amp;&#1500;&amp;&#1512;&amp;&#1491;&amp;&#1497;&amp;&#1511;&amp;&#1500; &amp;&#1513;&amp;&#1500; &amp;&#1488;&amp;&#1500;&amp;&#1490;&amp;&#1489;&amp;&#1512;&amp;&#1514; &amp;&#1500;&amp;&#1497; &amp;&#1502;&amp;&#1505;&amp;&#1493;&amp;&#1490; &amp;&#55349;&amp;&#56374;&amp;&#55349;&amp;&#56411; /</alttitle><alttitle>Borel orbits of type (3&amp;&#178;, 1&amp;&#178;&amp;&#8319;&amp;&#8315;&amp;&#8310;) in nilradical of Lie Algebra of type &amp;&#55349;&amp;&#56374;&amp;&#55349;&amp;&#56411;.</alttitle><cdparentID>81173584980002791</cdparentID><recordid>972ELLAD_ALMA21180371490002791</recordid><addsrcrecordid>9919462873002791</addsrcrecordid></search><sort><title>&#55349;&#56841;-orbits of type (3&#178;, 1&#178;&#8319;&#8315;&#8310;) in nilradical of Lie Algebra of type &#55349;&#56374;&#55349;&#56411; /</title><creationdate>2019</creationdate><author>Maree, Yazeed</author>\r\n" + 
			"</sort><facets><language>eng</language><creationdate>2019</creationdate><topic>Lie groups lat</topic><topic>&amp;&#1495;&amp;&#1489;&amp;&#1493;&amp;&#1512;&amp;&#1493;&amp;&#1514; &amp;&#1500;&amp;&#1497; heb</topic><topic>Lie algebras lat</topic><topic>g Symmetric spaces lat</topic><topic>g Topological groups lat</topic><topic>Nilpotent groups lat</topic><topic>&amp;&#1511;&amp;&#1489;&amp;&#1493;&amp;&#1510;&amp;&#1493;&amp;&#1514; &amp;&#1504;&amp;&#1497;&amp;&#1500;&amp;&#1508;&amp;&#1493;&amp;&#1496;&amp;&#1504;&amp;&#1496;&amp;&#1497;&amp;&#1493;&amp;&#1514; heb</topic><topic>g Finite groups lat</topic><topic>Algebras, Linear lat</topic><topic>&amp;&#1488;&amp;&#1500;&amp;&#1490;&amp;&#1489;&amp;&#1512;&amp;&#1492; &amp;&#1500;&amp;&#1497;&amp;&#1504;&amp;&#1497;&amp;&#1488;&amp;&#1512;&amp;&#1497;&amp;&#1514; heb</topic><topic>g Algebra, Universal lat</topic><topic>g Generalized spaces lat</topic><topic>g Mathematical analysis lat</topic><topic>Calculus of operations lat</topic><topic>Line geometry lat</topic><topic>Topology lat</topic><collection>&amp;&#1492;&amp;&#1495;&amp;&#1493;&amp;&#1490; &amp;&#1500;&amp;&#1502;&amp;&#1514;&amp;&#1502;&amp;&#1496;&amp;&#1497;&amp;&#1511;&amp;&#1492; - Department of Mathematics (MA)</collection><toplevel>online_resources</toplevel><prefilter>books</prefilter><rsrctype>books</rsrctype><creatorcontrib>Maree, Yazeed</creatorcontrib><creatorcontrib>&amp;&#1502;&amp;&#1512;&amp;&#1506;&amp;&#1497;, &amp;&#1497;&amp;&#1494;&amp;&#1497;&amp;&#1491;</creatorcontrib><creatorcontrib>Melnikov, Anna</creatorcontrib><creatorcontrib>&amp;&#1502;&amp;&#1500;&amp;&#1504;&amp;&#1497;&amp;&#1511;&amp;&#1493;&amp;&#1489;, &amp;&#1488;&amp;&#1504;&amp;&#1492;</creatorcontrib><creatorcontrib>Universit&amp;&#803;at H&amp;&#803;efah. ha-&amp;&#7716;ug le-matema&amp;&#7789;i&amp;&#7731;ah</creatorcontrib><creatorcontrib>&amp;&#1488;&amp;&#1493;&amp;&#1504;&amp;&#1497;&amp;&#1489;&amp;&#1512;&amp;&#1505;&amp;&#1497;&amp;&#1496;&amp;&#1514; &amp;&#1495;&amp;&#1497;&amp;&#1508;&amp;&#1492;. &amp;&#1492;&amp;&#1495;&amp;&#1493;&amp;&#1490; &amp;&#1500;&amp;&#1502;&amp;&#1514;&amp;&#1502;&amp;&#1496;&amp;&#1497;&amp;&#1511;&amp;&#1492;</creatorcontrib><genre>Academic theses</genre><genre>&amp;&#1514;&amp;&#1494;&amp;&#1493;&amp;&#1514; &amp;&#1488;&amp;&#1511;&amp;&#1491;&amp;&#1502;&amp;&#1497;&amp;&#1493;&amp;&#1514;</genre><genre>Academic dissertations</genre><genre>Bachelor&amp;amp;apos;s theses</genre><genre>Diploma theses</genre><genre>Dissertations, Academic (PhD)</genre><genre>Dissertations, Academic (Master)</genre><genre>Doctoral dissertations</genre><genre>Doctoral theses</genre><genre>Graduate dissertations</genre><genre>Graduate theses</genre><genre>Licentiate dissertations</genre><genre>Licentiate theses</genre><genre>Master&amp;amp;apos;s dissertations</genre><genre>Master&amp;amp;apos;s theses</genre><genre>Ph. D. dissertations</genre><genre>Ph. D. theses</genre><genre>Senior projects</genre><genre>Senior theses</genre><genre>Theses, Academic</genre><genre>Undergraduate theses</genre><genre>&amp;&#1514;&amp;&#1497;&amp;&#1494;&amp;&#1493;&amp;&#1514; &amp;&#1488;&amp;&#1511;&amp;&#1491;&amp;&#1502;&amp;&#1497;&amp;&#1493;&amp;&#1514;</genre><genre>&amp;&#1491;&amp;&#1497;&amp;&#1505;&amp;&#1512;&amp;&#1496;&amp;&#1510;&amp;&#1497;&amp;&#1493;&amp;&#1514; &amp;&#1488;&amp;&#1511;&amp;&#1491;&amp;&#1502;&amp;&#1497;&amp;&#1493;&amp;&#1514;</genre><genre>&amp;&#1506;&amp;&#1489;&amp;&#1493;&amp;&#1491;&amp;&#1493;&amp;&#1514; &amp;&#1490;&amp;&#1502;&amp;&#1512;</genre><genre>Informational works</genre><atoz>Others</atoz><newrecords>20201019_559</newrecords><toplevel>available</toplevel><newrecords>20201019_567</newrecords></facets><dedup><t>1</t><c3>&#55349;&#56841;orbitsoftype3212&#8319;&#8315;&#8310;innilradicalofliealgebraoftype&#55349;&#56374;&#55349;&#56411;</c3><c4>2019</c4><c5>9919479786502791</c5><f5>&#55349;&#56841;orbitsoftype3212&#8319;&#8315;&#8310;innilradicalofliealgebraoftype&#55349;&#56374;&#55349;&#56411;</f5><f6>2019</f6><f7>&#55349;&#56841; orbits of type 32 12&#8319;&#8315;&#8310; in nilradical of lie algebra of type &#55349;&#56374;&#55349;&#56411;</f7><f8>is</f8><f9>1 online resource (iv, 47 pagves) :</f9><f10>university of haifa</f10><f11>maree yazeed</f11><f20>9919479786502791</f20>\r\n" + 
			"</dedup><frbr><t>1</t><k1>$$Kmaree yazeed$$AA</k1><k3>$$K&amp;&#55349;&amp;&#56841; orbits of type 32 12&amp;&#8319;&amp;&#8315;&amp;&#8310; in nilradical of lie algebra of type &amp;&#55349;&amp;&#56374;&amp;&#55349;&amp;&#56411;$$AT</k3></frbr><delivery><delcategory>$$VAlma-D$$O972ELLAD_ALMA11185303160002791</delcategory><delcategory>$$VAlma-P$$O972ELLAD_ALMA21180371490002791</delcategory></delivery><ranking><booster1>1</booster1><booster2>1</booster2>\r\n" + 
			"</ranking><addata><aulast>Maree</aulast><aulast>&amp;&#1502;&amp;&#1512;&amp;&#1506;&amp;&#1497;, &amp;&#1497;&amp;&#1494;&amp;&#1497;&amp;&#1491;</aulast><aulast>Melnikov</aulast><aulast>&amp;&#1502;&amp;&#1500;&amp;&#1504;&amp;&#1497;&amp;&#1511;&amp;&#1493;&amp;&#1489;, &amp;&#1488;&amp;&#1504;&amp;&#1492;</aulast><aufirst>Yazeed</aufirst><au>Maree, Yazeed</au><addau>Melnikov, Anna</addau><addau>&amp;&#1502;&amp;&#1500;&amp;&#1504;&amp;&#1497;&amp;&#1511;&amp;&#1493;&amp;&#1489;, &amp;&#1488;&amp;&#1504;&amp;&#1492;</addau><addau>Universit&amp;&#803;at H&amp;&#803;efah. ha-&amp;&#7716;ug le-matema&amp;&#7789;i&amp;&#7731;ah</addau><addau>&amp;&#1488;&amp;&#1493;&amp;&#1504;&amp;&#1497;&amp;&#1489;&amp;&#1512;&amp;&#1505;&amp;&#1497;&amp;&#1496;&amp;&#1514; &amp;&#1495;&amp;&#1497;&amp;&#1508;&amp;&#1492;. &amp;&#1492;&amp;&#1495;&amp;&#1493;&amp;&#1490; &amp;&#1500;&amp;&#1502;&amp;&#1514;&amp;&#1502;&amp;&#1496;&amp;&#1497;&amp;&#1511;&amp;&#1492;</addau><btitle>&amp;&#55349;&amp;&#56841;-orbits of type (3&amp;&#178;, 1&amp;&#178;&amp;&#8319;&amp;&#8315;&amp;&#8310;) in nilradical of Lie Algebra of type &amp;&#55349;&amp;&#56374;&amp;&#55349;&amp;&#56411;</btitle><addtitle>&amp;&#1502;&amp;&#1505;&amp;&#1500;&amp;&#1493;&amp;&#1500;&amp;&#1497; &amp;&#1489;&amp;&#1493;&amp;&#1512;&amp;&#1500; &amp;&#1502;&amp;&#1505;&amp;&#1493;&amp;&#1490; (3&amp;&#178;, 1&amp;&#178;&amp;&#8319;&amp;&#8315;&amp;&#8310;) &amp;&#1489;&amp;&#1504;&amp;&#1497;&amp;&#1500;&amp;&#1512;&amp;&#1491;&amp;&#1497;&amp;&#1511;&amp;&#1500; &amp;&#1513;&amp;&#1500; &amp;&#1488;&amp;&#1500;&amp;&#1490;&amp;&#1489;&amp;&#1512;&amp;&#1514; &amp;&#1500;&amp;&#1497; &amp;&#1502;&amp;&#1505;&amp;&#1493;&amp;&#1490; &amp;&#55349;&amp;&#56374;&amp;&#55349;&amp;&#56411;</addtitle><date>2019</date><risdate>2019</risdate><format>dissertation</format><genre>book</genre><ristype>BOOK</ristype><notes>Includes bibliographical references (page 47).</notes><mis1>11185303160002791</mis1><mis1>21180371490002791</mis1></addata><browse><callnumber>$$I$$DZD270 .M29 2019$$EZD  270            M 29   2019$$T0</callnumber>\r\n" + 
			"</browse>\r\n" + 
			"</record>";
	
	private static final String XML_OPEN  = "<PrimoNMBib>";
	private static final String XML_CLOSE = "</PrimoNMBib>";
	
	@Test
	void testXml() {
		String escapedXml = StringEscapeUtils.escapeXml("the data might contain & or ! or % or ' or # etc");
		System.out.println("escapedXml="+escapedXml);
		escapedXml = StringEscapeUtils.escapeXml("𝘉-orbits of type (3², 1²ⁿ⁻⁶) in nilradical of Lie Algebra of type 𝐶𝑛");
		System.out.println("escapedXml="+escapedXml);
		
		
		String decodeXml = StringEscapeUtils.unescapeXml(escapedXml);
		System.out.println("decodeXml="+decodeXml);
		
		
		escapedXml = StringEscapeUtils.escapeXml("החוג למתמטיקה - Department of Mathematics (MA)");
		System.out.println("escapedXml="+escapedXml);
		
		
		decodeXml = StringEscapeUtils.unescapeXml(escapedXml);
		System.out.println("decodeXml="+decodeXml);
		
		escapedXml = StringEscapeUtils.escapeXml("Joe's Café & Bar ♫");
		System.out.println("escapedXml="+escapedXml);
		
		
		decodeXml = StringEscapeUtils.unescapeXml(escapedXml);
		System.out.println("decodeXml="+decodeXml);
		
		escapedXml = StringEscapeUtils.escapeXml("枪炮、病菌与钢铁 : 人类社会的命运 = Guns, Germs, and Steel : The Fates of Human Societies");
		System.out.println("escapedXml="+escapedXml);
		
		
		decodeXml = StringEscapeUtils.unescapeXml(escapedXml);
		System.out.println("decodeXml="+decodeXml);
		
		decodeXml = StringEscapeUtils.unescapeXml("&#x1d609;-orbits of type (3², 1²ⁿ⁻⁶) in nilradical of Lie Algebra of type &#x1d436;&#x1d45b;");
		System.out.println("decodeXml="+decodeXml);
		
	}

	@Test
	void testJson() {
		String escapedXml = org.apache.commons.text.StringEscapeUtils.escapeJson("the data might contain & or ! or % or ' or # etc");
		System.out.println("escapedXml="+escapedXml);
		escapedXml =  org.apache.commons.text.StringEscapeUtils.escapeJson("𝘉-orbits of type (3², 1²ⁿ⁻⁶) in nilradical of Lie Algebra of type 𝐶𝑛");
		System.out.println("escapedXml="+escapedXml);
		
		
		String decodeXml =  org.apache.commons.text.StringEscapeUtils.unescapeJson(escapedXml);
		System.out.println("decodeXml="+decodeXml);
		
		
		escapedXml =  org.apache.commons.text.StringEscapeUtils.escapeJson("החוג למתמטיקה - Department of Mathematics (MA)");
		System.out.println("escapedXml="+escapedXml);
		
		
		decodeXml = org.apache.commons.text.StringEscapeUtils.unescapeJson(escapedXml);
		System.out.println("decodeXml="+decodeXml);
		
		escapedXml = org.apache.commons.text.StringEscapeUtils.escapeJson("Joe's Café & Bar ♫");
		System.out.println("escapedXml="+escapedXml);
		
		
		decodeXml = org.apache.commons.text.StringEscapeUtils.unescapeJson(escapedXml);
		System.out.println("decodeXml="+decodeXml);
		
		escapedXml = org.apache.commons.text.StringEscapeUtils.escapeJson("枪炮、病菌与钢铁 : 人类社会的命运 = Guns, Germs, and Steel : The Fates of Human Societies");
		System.out.println("escapedXml="+escapedXml);
		
		
		decodeXml = org.apache.commons.text.StringEscapeUtils.unescapeJson(escapedXml);
		System.out.println("decodeXml="+decodeXml);
		
	}	
	@Test
	void testXml10() {
		String escapedXml = org.apache.commons.text.StringEscapeUtils.escapeXml10("the data might contain & or ! or % or ' or # etc");
		System.out.println("escapedXml="+escapedXml);
		escapedXml =  org.apache.commons.text.StringEscapeUtils.escapeXml10("𝘉-orbits of type (3², 1²ⁿ⁻⁶) in nilradical of Lie Algebra of type 𝐶𝑛");
		System.out.println("escapedXml="+escapedXml);
		
		
		String decodeXml =  org.apache.commons.text.StringEscapeUtils.unescapeXml(escapedXml);
		System.out.println("decodeXml="+decodeXml);
		
		
		escapedXml =  org.apache.commons.text.StringEscapeUtils.escapeXml10("החוג למתמטיקה - Department of Mathematics (MA)");
		System.out.println("escapedXml="+escapedXml);
		
		
		decodeXml = org.apache.commons.text.StringEscapeUtils.unescapeXml(escapedXml);
		System.out.println("decodeXml="+decodeXml);
		
		escapedXml = org.apache.commons.text.StringEscapeUtils.escapeXml10("Joe's Café & Bar ♫");
		System.out.println("escapedXml="+escapedXml);
		
		
		decodeXml = StringEscapeUtils.unescapeXml(escapedXml);
		System.out.println("decodeXml="+decodeXml);
		
		escapedXml = org.apache.commons.text.StringEscapeUtils.escapeXml10("枪炮、病菌与钢铁 : 人类社会的命运 = Guns, Germs, and Steel : The Fates of Human Societies");
		System.out.println("escapedXml="+escapedXml);
		
		
		decodeXml = StringEscapeUtils.unescapeXml(escapedXml);
		System.out.println("decodeXml="+decodeXml);
		
	}	
	
	@Test
	void testXml11() {
		String escapedXml = org.apache.commons.text.StringEscapeUtils.escapeXml11("the data might contain & or ! or % or ' or # etc");
		System.out.println("escapedXml="+escapedXml);
		escapedXml =  org.apache.commons.text.StringEscapeUtils.escapeXml11("𝘉-orbits of type (3², 1²ⁿ⁻⁶) in nilradical of Lie Algebra of type 𝐶𝑛");
		System.out.println("escapedXml="+escapedXml);
		
		
		String decodeXml =  org.apache.commons.text.StringEscapeUtils.unescapeXml(escapedXml);
		System.out.println("decodeXml="+decodeXml);
		
		
		escapedXml =  org.apache.commons.text.StringEscapeUtils.escapeXml11("החוג למתמטיקה - Department of Mathematics (MA)");
		System.out.println("escapedXml="+escapedXml);
		
		
		decodeXml = org.apache.commons.text.StringEscapeUtils.unescapeXml(escapedXml);
		System.out.println("decodeXml="+decodeXml);
		
		escapedXml = org.apache.commons.text.StringEscapeUtils.escapeXml11("Joe's Café & Bar ♫");
		System.out.println("escapedXml="+escapedXml);
		
		
		decodeXml = StringEscapeUtils.unescapeXml(escapedXml);
		System.out.println("decodeXml="+decodeXml);
		
		escapedXml = org.apache.commons.text.StringEscapeUtils.escapeXml11("枪炮、病菌与钢铁 : 人类社会的命运 = Guns, Germs, and Steel : The Fates of Human Societies");
		System.out.println("escapedXml="+escapedXml);
		
		
		decodeXml = StringEscapeUtils.unescapeXml(escapedXml);
		System.out.println("decodeXml="+decodeXml);
		
	}	
	@Test
	void testHtml() {
		String escapedXml = StringEscapeUtils.escapeXml("the data might contain & or ! or % or ' or # etc");
		System.out.println("escapedHtml="+escapedXml);
		escapedXml = StringEscapeUtils.escapeHtml("𝘉-orbits of type (3², 1²ⁿ⁻⁶) in nilradical of Lie Algebra of type 𝐶𝑛");
		System.out.println("escapedHtml="+escapedXml);
		
		
		String decodeXml = StringEscapeUtils.unescapeHtml(escapedXml);
		System.out.println("decodeHtml="+decodeXml);
		
		
		escapedXml = StringEscapeUtils.escapeHtml("החוג למתמטיקה - Department of Mathematics (MA)");
		System.out.println("escapedHtml="+escapedXml);
		
		
		decodeXml = StringEscapeUtils.unescapeHtml(escapedXml);
		System.out.println("decodeHtml="+decodeXml);
		
		escapedXml = StringEscapeUtils.escapeHtml("Joe's Café & Bar ♫");
		System.out.println("escapedHtml="+escapedXml);
		
		
		decodeXml = StringEscapeUtils.unescapeHtml(escapedXml);
		System.out.println("decodeHtml="+decodeXml);
		
		escapedXml = StringEscapeUtils.escapeHtml("枪炮、病菌与钢铁 : 人类社会的命运 = Guns, Germs, and Steel : The Fates of Human Societies");
		System.out.println("escapedHtml="+escapedXml);
		
		
		decodeXml = StringEscapeUtils.unescapeHtml(escapedXml);
		System.out.println("decodeHtml="+decodeXml);
		
	}

	@Test
	void testJava() {
		String escapedXml = StringEscapeUtils.escapeJava("the data might contain & or ! or % or ' or # etc");
		System.out.println("escapedJava="+escapedXml);
		escapedXml = StringEscapeUtils.escapeJava("𝘉-orbits of type (3², 1²ⁿ⁻⁶) in nilradical of Lie Algebra of type 𝐶𝑛");
		System.out.println("escapedJava="+escapedXml);
		
		
		String decodeXml = StringEscapeUtils.unescapeJava(escapedXml);
		System.out.println("decodeJava="+decodeXml);
		
		
		escapedXml = StringEscapeUtils.escapeJava("החוג למתמטיקה - Department of Mathematics (MA)");
		System.out.println("escapedJava="+escapedXml);
		
		
		decodeXml = StringEscapeUtils.unescapeJava(escapedXml);
		System.out.println("decodeJava="+decodeXml);
		
		escapedXml = StringEscapeUtils.escapeJava("Joe's Café & Bar ♫");
		System.out.println("escapedJava="+escapedXml);
		
		
		decodeXml = StringEscapeUtils.unescapeJava(escapedXml);
		System.out.println("decodeJava="+decodeXml);
		
		escapedXml = StringEscapeUtils.escapeJava("枪炮、病菌与钢铁 : 人类社会的命运 = Guns, Germs, and Steel : The Fates of Human Societies");
		System.out.println("escapedJava="+escapedXml);
		
		
		decodeXml = StringEscapeUtils.unescapeJava(escapedXml);
		System.out.println("decodeJava="+decodeXml);
		
	}

	@Test
	void testSQL() {
		String escapedXml = StringEscapeUtils.escapeSql("the data might contain & or ! or % or ' or # etc");
		System.out.println("escapedSql="+escapedXml);
		escapedXml = StringEscapeUtils.escapeSql("𝘉-orbits of type (3², 1²ⁿ⁻⁶) in nilradical of Lie Algebra of type 𝐶𝑛");
		System.out.println("escapedSql="+escapedXml);
		
		
		String decodeXml = StringEscapeUtils.unescapeJava(escapedXml);
		System.out.println("decodeSql="+decodeXml);
		
		
		escapedXml = StringEscapeUtils.escapeSql("החוג למתמטיקה - Department of Mathematics (MA)");
		System.out.println("escapedSql="+escapedXml);
		
		
		decodeXml = StringEscapeUtils.unescapeJava(escapedXml);
		System.out.println("decodeSql="+decodeXml);
		
		escapedXml = StringEscapeUtils.escapeSql("Joe's Café & Bar ♫");
		System.out.println("escapedSql="+escapedXml);
		
		
		decodeXml = StringEscapeUtils.unescapeJava(escapedXml);
		System.out.println("decodeSql="+decodeXml);
		
		escapedXml = StringEscapeUtils.escapeJava("枪炮、病菌与钢铁 : 人类社会的命运 = Guns, Germs, and Steel : The Fates of Human Societies");
		System.out.println("escapedSql="+escapedXml);
		
		
		decodeXml = StringEscapeUtils.unescapeJava(escapedXml);
		System.out.println("decodeSql="+decodeXml);
		
	}

	
	@Test
	void testXMLParsing() {

		
		SAXReader reader = new SAXReader();
		
		
		Document tpxml = null;
		try {
			tpxml=reader.read(new StringReader(xmlContentValid));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(tpxml.asXML());
		
		org.w3c.dom.Document doc = convertStringToXMLDocument(xmlContentValid);
		
		try {
			DOMSource domSource = new DOMSource(doc);
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.transform(domSource, result);
			System.out.println("XML IN String format is: \n" + writer.toString());
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Test
	void testNotEncodedXML() {

		
		org.w3c.dom.Document doc = convertStringToXMLDocument(xmlContentNotEncoded);
		
		try {
			System.out.println("Code Source org.w3c.dom.Document:" + doc.getClass().getProtectionDomain().getCodeSource());
			DOMSource domSource = new DOMSource(doc);
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.transform(domSource, result);
			System.out.println("Code Source tf:" + tf.getClass().getProtectionDomain().getCodeSource());
			System.out.println("Code Source transformer:" + transformer.getClass().getProtectionDomain().getCodeSource());
			
			String xmlAfterTransformation= writer.toString();
			System.out.println("XML IN String format is: \n" + xmlAfterTransformation);
			
			PrimoNMBibDocument primoNmbDoc=processPrimoNMBibDocumentR(xmlAfterTransformation);
			
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		
		
		
	}
	
	@Test
	void testPrimoNMBibDocument() {
		PrimoNMBibDocument primoNmbDoc=processPrimoNMBibDocumentR(XMLContectEncoded);
	}
	
	@Test
	void testPrimoNMBibDocumentR() {
		PrimoNMBibDocument primoNmbDoc=processPrimoNMBibDocumentR(XMLEncodReplace);

	}

	PrimoNMBibDocument processPrimoNMBibDocumentR(String xml) {
		PrimoNMBibDocument primoNmbDoc=null;
		try {
			int indxmlstart=xml.indexOf("<?xml");
			if(indxmlstart>-1) {
				int indxmlend=xml.indexOf("?>",indxmlstart);
				xml=xml.substring(indxmlend+2);
			}
			String pnxStr = xml.replaceAll("<.xml version=\"1.0\" encoding=\"UTF-8\".>","" );
			primoNmbDoc=PrimoNMBibDocument.Factory.parse(XML_OPEN + pnxStr + XML_CLOSE);
			PrimoNMBib primoNmb = primoNmbDoc.getPrimoNMBib();
			
			System.out.println("NMBibDocument:"+primoNmbDoc.toString());
			System.out.println("Title:"+primoNmbDoc.getPrimoNMBib().getRecordArray(0).getDisplay().getTitleArray(0));
			
		} catch (XmlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return primoNmbDoc;
		
	}
	
	@Test
	void testPrimoNMBibDocumentNE() {
		PrimoNMBibDocument primoNmbDoc=processPrimoNMBibDocumentR(xmlContentNotEncoded);
	}
	
    private static org.w3c.dom.Document convertStringToXMLDocument(String xmlString) 
    {
        //Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         
        //API to obtain DOM Document instance
        DocumentBuilder builder = null;
        try
        {
            //Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();
             
            //Parse the content to Document object
            org.w3c.dom.Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            return doc;
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return null;
    }	
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
