package task1;

import java.io.*;
import java.security.*;

// �����ֿ��࣬���ڴ洢�ϴ������ݣ����ɸ��ݹ�ϣֵ�ҵ���Ӧ�����ݡ�
// ʹ�þ�̬���������״δ������ֿ�󣬺���ͨ�������ֿ��ַ��ɶԲֿ�����ݽ��в�����
public class Repository {
	// �ֿ�·���������ֿ���
	private static String path;
	
	
	// ���òֿ�·����Ӧ�����ֿ���
	public static void setPath(String newPath) {
		path = newPath;
	}
			
	// �޲ι��췽��
	public Repository() {
		setPath("C:/Users/WBZ/Desktop" + '/' + "myRepository");  // ����Ĭ��·��������
		// �����ֿ��ļ��У�������readme�ĵ�
		try{
			File file = new File(path);
			if(!file.exists()) {
				file.mkdir();
				writeFile(path + '/' + "readme.txt", "Hello World!");
			}
			// ���ֿ������ڣ����½�ʧ��
			else System.out.println("�ֿ����ظ����½�ʧ�ܣ�");
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	// ���췽�����أ�����Ϊ�½��ֿ��ַ�Ͳֿ���
	public Repository(String myPath, String name) {
		setPath(myPath + '/' + name);
		// ���ݸ���·���Ͳֿ��������ֿ⣬������readme�ĵ�
		try{
			File file = new File(path);
			if(!file.exists()) {
				file.mkdir();
				writeFile(path + '/' + "readme.txt", "Hello World!");
			}
			// ���ֿ������ڣ����½�ʧ��
			else System.out.println("�ֿ����ظ����½�ʧ�ܣ�");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// ��ָ������д���ļ�
	private static void writeFile(String filename, String content) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(filename));
			out.write(content);
			out.close();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	// �ϴ����ݣ��ļ�������Ϊ���ݵ�hashֵ
	public static void upload(String content) {
		try {
			String currentPath = path + '/' + "initialName.txt";
			writeFile(currentPath, content);
			renameFile(currentPath, path + '/' + getHashValue(currentPath) + ".txt");
			File file = new File(currentPath);
			// ���ϴ�������ֿ������������ظ������ϴ�ʧ��
			if(file.exists()) {
				file.delete();
				System.out.println("�����ظ����ϴ�ʧ�ܣ�");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	// ���ݹ�ϣֵ�����ض�Ӧ������
	public static String search(String HashValue) throws IOException{
		BufferedReader in = new BufferedReader(new FileReader(path + '/' + HashValue + ".txt"));
        String line;
        StringBuffer sb = new StringBuffer();
        while ((line = in.readLine()) != null) {
            sb.append(line + "\n");
        }
        in.close(); 
        String str = sb.toString();
        return str;
	}
	
	// �ļ�����������
	private static void renameFile(String oldPathName, String newPathName) throws IOException {
        // �ɵ��ļ���Ŀ¼
        File oldName = new File(oldPathName);
        // �µ��ļ���Ŀ¼
        File newName = new File(newPathName);
        // ȷ���µ��ļ���������
        if (!newName.exists())
        	oldName.renameTo(newName);
    }
	
	// ��ȡ�ļ��ֽڣ��õ���ϣֵ
	private static byte[] SHA1Checksum(InputStream is) throws Exception {
		// ���ڼ���hashֵ���ļ�������
		byte[] buffer = new byte[1024];
		// ʹ��SHA1��ϣ/ժҪ�㷨
		MessageDigest complete = MessageDigest.getInstance("SHA-1");
		int numRead = 0;
		do {
			// ����numRead�ֽڵ�buffer��
			numRead = is.read(buffer);
			if(numRead > 0) {
				// ����buffer[0:numRead]�����ݸ���hashֵ
				complete.update(buffer, 0, numRead);
			}
			// �ļ��Ѷ��꣬�˳�ѭ��
		} while (numRead != -1);
		// �ر�������
		is.close();
		// ����SHA1��ϣֵ
		return complete.digest();
	}
	
	// �����ļ���ϣֵ�����ؽ��
	private static String getHashValue(String path) throws Exception {
		// �����ļ�����
		File file = new File(path);
		// Ϊ�ļ�����������
		FileInputStream is = new FileInputStream(file);
		// ������õ����ļ���ϣֵ���ֽڣ�����������
		byte[] sha1 = SHA1Checksum(is);
			
		String result = "";
		for(int i = 0; i < sha1.length; i++) {
			// ���õ��������ֽڹ�ϣֵ���㲹�룬��ת��Ϊ16����
			result += Integer.toString(sha1[i] & 0xFF, 16);
		}
		return result;
	}
	
}
