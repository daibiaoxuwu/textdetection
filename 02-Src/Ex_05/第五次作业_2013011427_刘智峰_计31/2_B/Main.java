// 参考   http://bbs.csdn.net/topics/260045802

import java.io.*;
public class Main {
	String pathA;
	String pathB;
	
	Main(String pathA , String pathB){
		this.pathA = pathA;
		this.pathB = pathB;
	}
	
	public static void main(String args[]){
		InputStreamReader ir = null;
		BufferedReader in = null;
		String a = ""; String b = "";
		try{
			System.out.println("please input file path of source");
			ir = new InputStreamReader(System.in);
			in = new BufferedReader(ir);
			a = in.readLine();
			System.out.println("please input file path of target");
			b = in.readLine();
		}catch(IOException e){
			System.out.println(e);
		}				
		Main m = new Main(a,b);
		m.copy(m.pathA , m.pathB);
	}
	
	public void copy(String pathA , String pathB){
		
		File singleFile = new File(pathA);
	
		String name = singleFile.getName();
		if(singleFile.isFile()){
			copyFile(singleFile , new File(pathB +  File.separator + singleFile.getName()));
		}
		else
		{
			(new File(pathB)).mkdirs(); //创建目标文件夹
			
			File[] file = (new File(pathA)).listFiles();
			for(int i = 0;i < file.length;i++){
				if(file[i].isFile()){
					//复制文件
					(new File(pathB + File.separator + singleFile.getName())).mkdirs();
					copyFile(file[i] , new File(pathB +  File.separator + singleFile.getName() + File.separator + file[i].getName()));
				}
				else if(file[i].isDirectory()){
	                // 复制目录   
	                String sourceDir=pathA + File.separator + file[i].getName();  
	                String targetDir=pathB + File.separator + singleFile.getName() + File.separator + file[i].getName();  
	                copyDirectiory(sourceDir, targetDir);  
				}
			}
		}


	}
	
	public void copyFile(File sourceFile , File targetFile){
		try{
		    FileInputStream is = new FileInputStream(sourceFile);  
	        BufferedInputStream in =new BufferedInputStream(is);
	        
	        FileOutputStream os = new FileOutputStream(targetFile);  
	        BufferedOutputStream on = new BufferedOutputStream(os);  
	        
	        // 缓冲数组   
	        byte[] b = new byte[1024 * 5];  
	        int len;  
	        while ((len = in.read(b)) != -1) {  
	            on.write(b, 0, len);  
	        }  
	        
	        // 刷新此缓冲的输出流   
	        on.flush();  
	          
	        //关闭流   
	        in.close();  
	        on.close();  
	        os.close();  
	        is.close(); 
	        
		}catch(IOException e){
			System.out.println(e);
		}
	}
	
	public void copyDirectiory(String sourceDir , String targetDir){

		(new File(targetDir)).mkdirs();
		 File[] file = (new File(sourceDir)).listFiles(); 
		 for (int i = 0; i < file.length; i++) {  
			 if(file[i].isFile()){
				 File sourceFile = file[i];
				 File targetFile = new File(new File(targetDir).getAbsolutePath() + File.separator+file[i].getName());
				 copyFile(sourceFile , targetFile);
			 }
			 else if(file[i].isDirectory()){
				 String dir1 = sourceDir + "/" + file[i].getName();
				 String dir2 = targetDir + "/"+ file[i].getName(); 
				 copyDirectiory(dir1, dir2);  
				 
			 }
		 }

	}
}
