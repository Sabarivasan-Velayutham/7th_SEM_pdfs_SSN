#include <GL/glut.h>
#include <iostream>
#include <math.h>
#define PI 3.14159
using namespace std;

/***UTILITY FUNCTIONS***/
double xm = 125, xM = 375, ym = 125, yM = 375;
double v1[] = {40, 160, 1}, v2[] = {300, 160, 1}, v3[] = {90, 260, 1};

// quad drawing function
void obj(double x1[], double x2[], double x3[])
{
	glBegin(GL_QUADS);
	glVertex2f(x1[0], x1[1]);
	glVertex2f(x2[0], x2[1]);
	glVertex2f(x3[0], x3[1]);
	glVertex2f(x1[0], x1[1]);
	glEnd();
}
void frame()
{
	glBegin(GL_LINES);
	glVertex2f(xm, ym);
	glVertex2f(xM, ym);
	glEnd();
	glBegin(GL_LINES);
	glVertex2f(xM, ym);
	glVertex2f(xM, yM);
	glEnd();
	glBegin(GL_LINES);
	glVertex2f(xM, yM);
	glVertex2f(xm, yM);
	glEnd();
	glBegin(GL_LINES);
	glVertex2f(xm, yM);
	glVertex2f(xm, ym);
	glEnd();
}

/***QUESTION FUNCTIONS***/
void wtov(double v[])
{
	// scaling factors
	double sx = (xM - xm) / (500 - 0);
	double sy = (yM - ym) / (500 - 0);
	// viewpoint coordinates
	v[0] = xm + ((v[0] - 0) * sx);
	v[1] = ym + ((v[1] - 0) * sy);
}

/***DISPLAY FUNCTIONS***/
void disp()
{
	glClearColor(0, 0, 0, 1);

	// object
	glColor3f(0, 1, 0);
	obj(v1, v2, v3);

	// frame
	glColor3f(0, 0, 1);
	frame();

	// transformed object
	wtov(v1);
	wtov(v2);
	wtov(v3);
	obj(v1, v2, v3);

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
	gluOrtho2D(0, 500, 0, 500);
	glutDisplayFunc(disp);
	glutMainLoop();
	return 0;
}
