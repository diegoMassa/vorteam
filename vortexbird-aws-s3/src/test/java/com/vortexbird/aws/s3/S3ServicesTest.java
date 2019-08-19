package com.vortexbird.aws.s3;

import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration("/applicationContextAWS.xml")
public class S3ServicesTest {
	
	// TODO: FEDAZA-> Actualizar Test para que use Junit5
	
//	private final static Logger log=LoggerFactory.getLogger(S3ServicesTest.class);
//	
//	@Autowired
//	private IS3Services s3Services;
//	
//	private String bucketName="vortex-dgomez-"+UUID.randomUUID();
//	
//	private String key="client/documento.pdf";
//	
//	private File file=new File("/Users/dgomez/Workspaces/workspace-oxygen-aws/aws-s3/src/main/resources/log4j2.xml");
//	
//	@Test
//	public void test()throws Exception {		
//		createBucket();
//		listBucketsName();
//		uploadPublicRead();
//		download();
//		deleteObject();
//		deleteBucket();		
//	}	
//	
//	public void createBucket()throws Exception{
//		assertNotNull(s3Services);
//		s3Services.createBucket(bucketName);
//	}
//	
//	public void listBucketsName()throws Exception{
//		assertNotNull(s3Services);
//		List<String> listName=s3Services.listBucketsName();
//		assertNotNull(listName);
//		for (String name : listName) {
//			log.info(name);
//		}
//	}
//	
//	public void uploadPublicRead() throws Exception{
//		assertNotNull(s3Services);
//		String urlObject=s3Services.uploadPublicRead(bucketName, key, file);
//		log.info("url:"+urlObject);
//	}
//	
//	public void download()throws Exception{
//		assertNotNull(s3Services);
//		InputStream inputStream=s3Services.download(bucketName, key);
//		assertNotNull(inputStream);
//	}
//	
//	public void deleteObject()throws Exception{
//		assertNotNull(s3Services);
//		s3Services.deleteObject(bucketName, key);
//	}
//	 
//	public void deleteBucket()throws Exception{
//		assertNotNull(s3Services);
//		s3Services.deleteBucket(bucketName);
//	}

}
