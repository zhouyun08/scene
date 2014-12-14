package first;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *@author zhouyun
 *2014-6-6����8:40:52
 */
public class Vertex {
	
	public static float[] testVertex(String vertexName)
	{
		int n=0;

	   	float[] min=new float[3];
		float[] max=new float[3];
		float[][] aVertexCoor=new float[65535][3];
		
		float[] xyzLen=new float[3];  //XYZ���߳�
		
		try
		{
			FileReader fr=new FileReader(vertexName);
			BufferedReader br=new BufferedReader(fr);
			String a;
				
			while ((a=br.readLine())!=null)
			{
	
				if(a.indexOf('v')==0&&a.indexOf('t')!=1&&a.indexOf('n')!=1)
				{
					
					n++;
					int alen=a.length();
					String aStr=null;
					aStr=a.substring(2,alen);		//�����ȥ��ʶV���ո���������ֵ��ɵ��ַ���
				//	System.out.println(aStr);
					
					String[] aVertex=aStr.trim().split(" ");
			
					for(int i=0;i<3;i++)
					{
						aVertexCoor[n][i]=Float.parseFloat(aVertex[i]);

					}
				}
		
			}
			fr.close();
			br.close();
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
		
	//	System.out.println("һ��n������㣺"+n);
		
//��������С�����		
		for(int j=0;j<3;j++)
		{
			min[j]=aVertexCoor[1][j];
			max[j]=aVertexCoor[1][j];
		}
		for(int i=2;i<=n;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(aVertexCoor[i][j]>max[j])
					max[j]=aVertexCoor[i][j];
				if(aVertexCoor[i][j]<min[j])
					min[j]=aVertexCoor[i][j];
			}
		}

		for(int i=0;i<3;i++)
		{
			xyzLen[i]=max[i]-min[i];
		}
		
	//	System.out.println("X�����min:"+min[0]+" max:"+max[0]+" X��ĳ���"+XYZlen[0]);
	//	System.out.println("Y�����min:"+min[1]+" max:"+max[1]+" Y��ĳ���"+XYZlen[1]);
	//	System.out.println("Z�����min:"+min[2]+" max:"+max[2]+" Z��ĳ���"+XYZlen[2]);
		
		float maxLength=xyzLen[0];		//����������һ��
		if(xyzLen[1]>maxLength)
			maxLength=xyzLen[1];
		if(xyzLen[2]>maxLength)
			maxLength=xyzLen[2];
		
		float ratio=maxLength/2;
	//	System.out.println("���ű�����"+ratio);
	
		for(int i=0;i<3;i++)
		{
			xyzLen[i]/=ratio;
	//		XYZlen[i]/=2;		//Location�����ñ�����Ϊ0.5���������Ӧ��С2��
			xyzLen[i]/=2;		//���߿��һ��
		}
	//	System.out.println("����"+XYZlen[0]);
	//	System.out.println("�ߣ�"+XYZlen[1]);
	//	System.out.println("��"+XYZlen[2]);	
		return xyzLen;
	}

/*	
	public static void main(String[] args) {
		float len1[]=new Vertex().testVertex("����.obj");
		float len2[]=new Vertex().testVertex("����.obj");
		for(int i=0;i<3;i++)
		{
			System.out.println(len1[i]+" "+len2[i]);	
		}
	}
*/

}
