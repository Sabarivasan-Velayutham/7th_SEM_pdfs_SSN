#include <GL/glut.h>
#include <iostream>
#include <math.h>
using namespace std;

void point(int x, int y)
{
	glBegin(GL_POINTS);
	glVertex2f(x, y);
	glEnd();
}

void dda(int x0, int y0, int x1, int y1)
{
	float dx = x1 - x0;
	float dy = y1 - y0;
	float steps = fabs(dx) > fabs(dy) ? fabs(dx) : fabs(dy);
	float xIncrement = dx / steps;
	float yIncrement = dy / steps;
	float x = x0;
	float y = y0;

	for (int i = 0; i <= steps; ++i)
	{
		point((int)(x + 0), (int)(y + 0));
		x += xIncrement;
		y += yIncrement;
	}
}

void disp()
{
	glClearColor(0, 0, 0, 1);
	glColor3f(0, 1, 0);

	dda(0, 0, 75, 75);

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
