package task1;

import java.io.IOException;

public class Test {

	public static void main(String[] args) throws IOException {
		// �����вι��췽���½�һ���ֿ�
		new Repository("C:/Users/WBZ/Desktop", "r1");
		// ���òֿ�·�������½����ֿ����Ҫ�趨�ֿ��ַ�Բֿ���в���
//		Repository.setPath("C:/Users/WBZ/Desktop/r1");
		// �ϴ�����
		Repository.upload("helloEveryone");
		Repository.upload("goodbye\nimgone");
		Repository.upload("helloEveryone");
		// ͨ��hashֵ��������
//		System.out.print(Repository.search("7c51603df7dddf74445b9930b068d7ea379a4224"));
	}
}

