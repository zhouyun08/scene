package second;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

import com.sun.j3d.loaders.Scene;

import first.J3DLoader;
import first.Vertex;


/**
 *@author zhouyun
 *2014-6-14����10:39:21
 */
public class BodyProcesser {

	
	public void test1(Body b1, Body b2, String location){
		
		if(location.equals("up"))
		{
			b2.getCore_pos().y3d =b1.getCore_pos().y3d+b1.height+b2.height;
		}
		if(location.equals("right"))
		{
			b2.getCore_pos().y3d =b1.getCore_pos().y3d-b1.height+b2.height;
			b2.getCore_pos().x3d=b1.getCore_pos().x3d+b1.length+b2.length;
		}
		if(location.equals("left"))
		{
			b2.getCore_pos().y3d =b1.getCore_pos().y3d-b1.height+b2.height;
			b2.getCore_pos().x3d=b1.getCore_pos().x3d-(b1.length+b2.length);
		}
		if(location.equals("front"))
		{
			b2.getCore_pos().x3d =b1.getCore_pos().x3d;
			b2.getCore_pos().y3d =b1.getCore_pos().y3d-b1.height+b2.height;
			b2.getCore_pos().z3d=b1.getCore_pos().z3d+(b1.width+b2.width);
		}
		if(location.equals("back"))
		{
			b2.getCore_pos().x3d =b1.getCore_pos().x3d;
			b2.getCore_pos().y3d =b1.getCore_pos().y3d-b1.height+b2.height;
			b2.getCore_pos().z3d=b1.getCore_pos().z3d-(b1.width+b2.width);
		}
	}

	/*
	public static void main(String[] args){		
		
		Body b1=new Body();
		Body b2=new Body();
		b1.testBody("����.obj");
		b2.testBody("����.obj");
		J3DLoader loader = new J3DLoader();
		Scene scene1 = loader.loadObj("����.obj");
		Scene scene2 = loader.loadObj("����.obj");
		BodyProcesser  bp = new BodyProcesser();
		bp.test1(b1, b2, "up");
        
	}
	*/
}



