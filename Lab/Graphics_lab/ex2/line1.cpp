
#include <GL/glut.h>
#include <iostream>
#include <cmath>

using namespace std;

void myInit()
{
    glClearColor(1.0, 1.0, 1.0, 0.0);
    glColor3f(0.0f, 0.0f, 0.0f);
    glPointSize(10);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluOrtho2D(-150.0, 640.0, -150.0, 480.0);
}

void myDisplay()
{

    glClear(GL_COLOR_BUFFER_BIT);
    float x0, y0, xn, yn, x, y, m;
    cin >> x0 >> y0 >> xn >> yn;
    m = (yn - y0) / (xn - x0);
    x = x0;
    y = y0;
    glBegin(GL_POINTS);
    if (x0 < xn)
    {
        if (m > 0)
        {
            if (abs(m) < 1)
            {
                while (x != xn)
                {
                    glVertex2d((int)round(x), (int)round(y));
                    x += 1;
                    y += m;
                }
            }

            else
            {
                while (y != yn)
                {
                    glVertex2d((int)round(x), (int)round(y));
                    x += 1 / m;
                    y += 1;
                }
            }
        }

        else
        {
            if (abs(m) < 1)
            {
                while (x != xn)
                {
                    glVertex2d((int)round(x), (int)round(y));
                    x += 1;
                    y -= m;
                }
            }

            else
            {
                while (y != yn)
                {
                    glVertex2d((int)round(x), (int)round(y));
                    x += 1 / m;
                    y -= 1;
                }
            }
        }
    }

    else
    {
        if (m > 0)
        {
            if (abs(m) < 1)
            {
                while (x != xn)
                {
                    glVertex2d((int)round(x), (int)round(y));
                    x -= 1;
                    y -= m;
                }
            }

            else
            {
                while (y != yn)
                {
                    glVertex2d((int)round(x), (int)round(y));
                    x -= 1 / m;
                    y -= 1;
                }
            }
        }

        else
        {
            if (abs(m) < 1)
            {
                while (x != xn)
                {
                    glVertex2d((int)round(x), (int)round(y));
                    x -= 1;
                    y += m;
                }
            }

            else
            {
                while (y != yn)
                {
                    glVertex2d((int)round(x), (int)round(y));
                    x -= 1 / m;
                    y += 1;
                }
            }
        }
    }

    glEnd();

    glFlush();
}

int main(int argc, char *argv[])
{

    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
    glutInitWindowSize(640, 480);
    glutInitWindowPosition((glutGet(GLUT_SCREEN_WIDTH) - 640) / 2, (glutGet(GLUT_SCREEN_HEIGHT) - 480) / 2);
    glutCreateWindow("Second Exercise");
    glutDisplayFunc(myDisplay);
    myInit();
    glutMainLoop();

    return 1;
}