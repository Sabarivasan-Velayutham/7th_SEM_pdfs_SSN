#include <GL/glut.h>
#include <iostream>
#include <math.h>
#define PI 3.14159
using namespace std;

/***UTILITY FUNCTIONS***/
// point and matrix info
double v1[] = {10, 10, 1}, v2[] = {10, 60, 1}, v3[] = {60, 60, 1}, v4[] = {60, 10, 1};
double mat[][3] = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
// quad drawing function
void quad(double v1[], double v2[], double v3[], double v4[])
{
	glBegin(GL_QUADS);
	glVertex2f(v1[0], v1[1]);
	glVertex2f(v2[0], v2[1]);
	glVertex2f(v3[0], v3[1]);
	glVertex2f(v4[0], v4[1]);
	glEnd();
}
// multiply function
void mm(double m[3][3], double v[3])
{
	double res[3];
	for (int i = 0; i < 3; ++i)
	{
		double temp = 0;
		for (int k = 0; k < 3; ++k)
			temp += m[i][k] * v[k];
		res[i] = temp;
	}
	for (int i = 0; i < 3; ++i)
		v[i] = res[i];
}

/***QUESTION FUNCTIONS***/
void translate(double tx, double ty)
{
	mat[0][2] = tx;
	mat[1][2] = ty;
}
void rotate(double t)
{
	mat[0][0] = cos(t);
	mat[0][1] = -sin(t);
	mat[1][0] = sin(t);
	mat[1][1] = cos(t);
}
void pivrotate(double t, double xr, double yr)
{
	mat[0][0] = cos(t);
	mat[0][1] = -sin(t);
	mat[1][0] = sin(t);
	mat[1][1] = cos(t);
	mat[0][2] = xr * (1 - cos(t)) + yr * sin(t);
	mat[1][2] = yr * (1 - cos(t)) - yr * sin(t);
}
void scale(double sx, double sy)
{
	mat[0][0] = sx;
	mat[1][1] = sy;
}
void pivscale(double sx, double sy, double xr, double yr)
{
	mat[0][0] = sx;
	mat[1][1] = sy;
	mat[0][2] = xr * (1 - sx);
	mat[1][2] = yr * (1 - sy);
}
void reflectX() { mat[0][0] = -1; }
void reflectY() { mat[1][1] = -1; }
void reflectXY()
{
	mat[0][0] = -1;
	mat[1][1] = -1;
}
void reflectXEY()
{
	mat[0][0] = 0;
	mat[1][1] = 0;
	mat[0][1] = 1;
	mat[1][0] = 1;
}
void shearX(double shx) { mat[0][1] = shx; }
void shearY(double shy) { mat[1][0] = shy; }
void shearXyRef(double shx, double yref)
{
	mat[0][1] = shx;
	mat[0][2] = -shx * yref;
}
void shearYxRef(double shy, double xref)
{
	mat[1][0] = shy;
	mat[1][2] = -shy * xref;
}

/***DISPLAY FUNCTIONS***/
void disp()
{
	glClearColor(0, 0, 0, 1);
	glColor3f(1, 1, 1);
	glBegin(GL_LINES);
	glVertex2f(0, 250);
	glVertex2f(0, -250);
	glEnd(); //-------------Y AXIS
	glBegin(GL_LINES);
	glVertex2f(250, 0);
	glVertex2f(-250, -0);
	glEnd(); //-------------X AXIS

	// ORIGINAL OBJECT
	glColor3f(0, 1, 0);
	quad(v1, v2, v3, v4);

	// TRANSFORMATION
	int choice = 1;
	cout << "Enter choice: ";
	cin >> choice;
	switch (choice)
	{
	case 1:
		translate(100, 100);
		break;
	case 2:
		rotate(3 * PI / 4);
		break;
	case 3:
		pivrotate(PI / 4, -100, -200);
		break;
	case 4:
		scale(-2, 2);
		break;
	case 5:
		pivscale(2, 2, 150, 150);
		break;
	case 6:
		reflectX();
		break;
	case 7:
		reflectY();
		break;
	case 8:
		reflectXY();
		break;
	case 9:
		reflectXEY();
		break;
	case 10:
		shearX(2);
		break;
	case 11:
		shearY(2);
		break;
	case 12:
		shearXyRef(0.5, -100);
		break;
	case 13:
		shearYxRef(0.5, -100);
		break;
	default:
		cout << "error";
		break;
	}
	mm(mat, v1);
	mm(mat, v2);
	mm(mat, v3);
	mm(mat, v4);

	// TRANSFORMED OBJECT
	glColor3f(0, 0, 1);
	quad(v1, v2, v3, v4);

	glFlush();
}
int main(int ac, char *av[])
{
	glutInit(&ac, av);
	glutInitWindowSize(500, 500);
	glutInitWindowPosition(0, 0);
	glutCreateWindow("WINDOW");
	//---------------------------------
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluOrtho2D(-250, 250, -250, 250);
	glutDisplayFunc(disp);
	glutMainLoop();
	return 0;
}
