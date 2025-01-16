

#include <GL/glut.h>
#include <stdio.h>
#include <stdlib.h>
#include <math.h>

void myInit()
{
    glClearColor(1.0, 1.0, 1.0, 0.0);
    glColor3f(0.0f, 0.0f, 0.0f);
    glPointSize(4);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluOrtho2D(-320.0, 320.0, -240.0, 240.0);
}

void circle(int xc, int yc, int r)
{
    glBegin(GL_POINTS);
    int x = 0, y = r;
    int p = 1 - r;
    glVertex2d(x + xc, y + yc);
    glVertex2d(x + xc, -y + yc);
    glVertex2d(-x + xc, y + yc);
    glVertex2d(-x + xc, -y + yc);
    while (y > x)
    {
        x++;
        if (p < 0)
        {
            p = p + (2 * x) + 1;
        }
        else
        {
            y--;
            p = p + (2 * x) - (2 * y) + 1;
        }
        glVertex2d(x + xc, y + yc);
        glVertex2d(x + xc, -y + yc);
        glVertex2d(-x + xc, y + yc);
        glVertex2d(-x + xc, -y + yc);
        glVertex2d(y + xc, x + yc);
        glVertex2d(y + xc, -x + yc);
        glVertex2d(-y + xc, x + yc);
        glVertex2d(-y + xc, -x + yc);
    }
    glEnd();
}

void rover(int tx = 0, int ty = 0)
{
    // moon
    glColor3f(0.5, 0.5, 0.5);
    circle(0, 0, 225);
    // rover = rect + lines
    glColor3f(0.6f, 0.6f, 0.6f);
    glBegin(GL_QUADS);
    glVertex2d(-25 + tx, -25 + ty);
    glVertex2d(-25 + tx, 25 + ty);
    glVertex2d(25 + tx, 25 + ty);
    glVertex2d(25 + tx, -25 + ty);
    glEnd();

    glBegin(GL_LINES);
    glColor3f(1.0, 0.6, 0.2);
    glVertex2d(-8 + tx, 8 + ty);
    glVertex2d(8 + tx, 8 + ty);
    glColor3f(1.0, 1.0, 1.0);
    glVertex2d(-8 + tx, 0 + ty);
    glVertex2d(8 + tx, 0 + ty);
    glColor3f(0.2, 1.0, 0.2);
    glVertex2d(-8 + tx, -8 + ty);
    glVertex2d(8 + tx, -8 + ty);
    glEnd();

    glBegin(GL_POINTS);
    glColor3f(0.2, 0.2, 1.0);
    glVertex2d(0 + tx, 0 + ty);
    glEnd();
    // wheels
    glColor3f(0.0, 0.0, 0.0);
    circle(-15 + tx, -30 + ty, 5);
    circle(15 + tx, -30 + ty, 5);
}

void scene()
{
    glClear(GL_COLOR_BUFFER_BIT);

    rover();
    rover(75, 0);

    glFlush();
}

int main(int argc, char *argv[])
{
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
    glutInitWindowSize(640, 480);
    glutCreateWindow("Lab Test - R82");
    myInit();
    glutDisplayFunc(scene);
    glutMainLoop();
    return 0;
}