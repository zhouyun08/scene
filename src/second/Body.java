package second;

import first.Vertex;

/**
 *@author zhouyun
 *2014-6-13ÏÂÎç8:46:26
 */
class Point3d{
	public float x3d;
	public float y3d;
	public float z3d;
}

public class Body {

	float length;
	float height;
	float width;
	Point3d core_pos;	
	String objName;	
	Body(){
		
		
		
		
		core_pos = new Point3d();
		
	}
		
	public void testBody(String objFileName)
	{
		float lhw[] = new float[3];
		lhw = Vertex.testVertex(objFileName);
		this.length = lhw[0];
		this.height = lhw[1];
		this.width = lhw[2];

		this.core_pos.x3d = 0;
		this.core_pos.y3d = -(2.0f-this.height);
		this.core_pos.z3d = 0;	
		
		this.objName=objFileName;
	}
	void setPoint3d(float x, float y, float z){
		this.core_pos.x3d = x;
		this.core_pos.y3d = y;
		this.core_pos.z3d = z;
	}
	public float getLength() {
		
		
		
		
		return length;
	}
	public void setLength(float length) {
		
		
		
		
		
		this.length = length;
	}
	public float getHeight() {
		
		
		
		
		
		
		
		return height;
	}
	public void setHeight(float height) {
		
		
		
		
		
		
		
		this.height = height;
	}
	public float getWidth() {
		
		
		
		
		
		return width;
	}
	public void setWidth(float width) {
		
		
		
		
		
		this.width = width;
	}
	public Point3d getCore_pos() {
		
		
		
		
		
		return core_pos;
	}
	public void setCore_pos(Point3d core_pos) {
		
		
		
		
		
		
		this.core_pos = core_pos;
	}

}
