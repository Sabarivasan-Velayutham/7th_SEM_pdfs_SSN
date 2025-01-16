
#include <GL/glut.h>
#include <iostream>
#include <cmath>
using namespace std;

void swap(int *a, int *b)
{
    int c = *a;
    *a = *b;
    *b = c;
}

void myInit()
{
    glClearColor(0.5, 1.0, 1.0, 0.0);
    glColor3f(0.0f, 0.0f, 0.0f);
    glPointSize(1);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluOrtho2D(-320.0, 320.0, -240.0, 240.0);
}

void bresenham()
{
    glClear(GL_COLOR_BUFFER_BIT);
    int xa, ya, xb, yb, dx, dy, p, xi, yi, x, y;
    printf("Enter x0, y0, xn, yn: ");
    scanf("%d %d %d %d", &xa, &ya, &xb, &yb);
    glRasterPos2d(xa, ya - 15);
    glutBitmapCharacter(GLUT_BITMAP_HELVETICA_18, int('A'));
    glRasterPos2d(xb, yb - 15);
    glutBitmapCharacter(GLUT_BITMAP_HELVETICA_18, int('B'));

    glBegin(GL_LINES);
    glVertex2d(0, -240);
    glVertex2d(0, 240);
    glVertex2d(-320, 0);
    glVertex2d(320, 0);
    glEnd();

    glBegin(GL_POINTS);
    if (abs(yb - ya) < abs(xb - xa))
    {
        if (xa > xb)
        {
            swap(&xa, &xb);
            swap(&ya, &yb);
        }
        dx = xb - xa;
        dy = yb - ya;
        yi = 1;
        if (dy < 0)
        {
            yi = -1;
            dy = -dy;
        }
        p = (2 * dy) - dx;
        y = ya;
        for (x = xa; x < xb; x++)
        {
            glVertex2d(x, y);
            if (p > 0)
            {
                y = y + yi;
                p = p + (2 * (dy - dx));
            }
            else
            {
                p = p + (2 * dy);
            }
        }
    }
    else
    {
        if (ya > yb)
        {
            swap(&ya, &yb);
            swap(&xa, &xb);
        }
        dx = xb - xa;
        dy = yb - ya;
        xi = 1;
        if (dx < 0)
        {
            xi = -1;
            dx = -dx;
        }
        p = (2 * dx) - dy;
        x = xa;
        for (y = ya; y < yb; y++)
        {
            glVertex2d(x, y);
            if (p > 0)
            {
                x = x + xi;
                p = p + (2 * (dx - dy));
            }
            else
            {
                p = p + (2 * dx);
            }
        }
    }
    glEnd();
    glFlush();
}

int main(int argc, char **argv)
{
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
    glutInitWindowSize(640, 480);
    glutCreateWindow("Bresenham");
    glutDisplayFunc(bresenham);
    myInit();
    glutMainLoop();
    return 0;
}