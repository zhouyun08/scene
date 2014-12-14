package first;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *@author zhouyun
 *2014-6-6下午8:40:52
 */
public class Vertex {
	
	public static float[] testVertex(String vertexName)
	{
		int n=0;

	   	float[] min=new float[3];
		float[] max=new float[3];
		float[][] aVertexCoor=new float[65535][3];
		
		float[] xyzLen=new float[3];  //XYZ各边长
		
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
					aStr=a.substring(2,alen);		//保存出去标识V及空格后的三个数值组成的字符串
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
		
	//	System.out.println("一共n个坐标点："+n);
		
//求最大和最小坐标点		
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
		
	//	System.out.println("X坐标的min:"+min[0]+" max:"+max[0]+" X轴的长："+XYZlen[0]);
	//	System.out.println("Y坐标的min:"+min[1]+" max:"+max[1]+" Y轴的长："+XYZlen[1]);
	//	System.out.println("Z坐标的min:"+min[2]+" max:"+max[2]+" Z轴的长："+XYZlen[2]);
		
		float maxLength=xyzLen[0];		//长宽高中最长的一边
		if(xyzLen[1]>maxLength)
			maxLength=xyzLen[1];
		if(xyzLen[2]>maxLength)
			maxLength=xyzLen[2];
		
		float ratio=maxLength/2;
	//	System.out.println("缩放比例："+ratio);
	
		for(int i=0;i<3;i++)
		{
			xyzLen[i]/=ratio;
	//		XYZlen[i]/=2;		//Location中设置比例了为0.5，长宽高相应缩小2倍
			xyzLen[i]/=2;		//长高宽的一半
		}
	//	System.out.println("长："+XYZlen[0]);
	//	System.out.println("高："+XYZlen[1]);
	//	System.out.println("宽："+XYZlen[2]);	
		return xyzLen;
	}

/*	
	public static void main(String[] args) {
		float len1[]=new Vertex().testVertex("桌子.obj");
		float len2[]=new Vertex().testVertex("风扇.obj");
		for(int i=0;i<3;i++)
		{
			System.out.println(len1[i]+" "+len2[i]);	
		}
	}
*/

}
