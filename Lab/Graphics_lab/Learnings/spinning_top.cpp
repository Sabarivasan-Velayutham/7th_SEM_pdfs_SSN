
#include <GL/glut.h>
#include <iostream>
#include <vector>
#include <cmath>

using namespace std;

#define vvd vector<vector<double>>
#define vd vector<double>

vvd triangle = {{-120, 100, 1}, {120, 100, 1}, {0, 0, 1}};
vvd tip = {{-10, 160, 1}, {10, 160, 1}, {10, 100, 1}, {-10, 100, 1}};

vvd multiply(vvd A, vvd B)
{
    vvd C(A.size(), vd(B[0].size(), 0));
    for (int i = 0; i < A.size(); i++)
    {
        for (int j = 0; j < B[0].size(); j++)
        {
            for (int k = 0; k < B.size(); k++)
            {
                C[i][j] += A[i][k] * B[k][j];
            }
        }
    }

    for (int i = 0; i < A.size(); i++)
    {
        for (int j = 0; j < B[0].size(); j++)
            C[i][j] = round(C[i][j]);
    }
    return C;
}

vvd rotate(vvd P, double deg)
{
    double rad = (M_PI / 180) * deg;
    vvd R(3, vd(3, 0));
    R[0][0] = R[1][1] = cos(rad);
    R[1][0] = sin(rad);
    R[0][1] = -1 * sin(rad);
    R[2][2] = 1;
    return multiply(R, P);
}

vvd transpose(vvd A)
{
    vvd T(A[0].size(), vd(A.size()));
    for (int i = 0; i < A.size(); i++)
    {
        for (int j = 0; j < A[0].size(); j++)
        {
            T[j][i] = A[i][j];
        }
    }

    return T;
}

void myInit()
{
    glClearColor(1.0, 1.0, 1.0, 0.0);
    glColor3f(0.0f, 0.0f, 0.0f);
    glPointSize(10);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluOrtho2D(-320.0, 320.0, -240.0, 240.0);
}

int i = 0;
int mul = 1;

void func(int s)
{
    if (i == 4)
    {
        mul = -1;
    }

    if (i == 12)
    {
        mul = 1;
        i = 0;
    }

    i++;
    triangle = transpose(triangle);
    tip = transpose(tip);
    triangle = transpose(rotate(triangle, 10 * mul));
    tip = transpose(rotate(tip, 10 * mul));
    glClear(GL_COLOR_BUFFER_BIT);
    glBegin(GL_POLYGON);

    for (auto point : triangle)
    {
        glVertex3f(point[0], point[1], point[2]);
    }

    glEnd();
    glBegin(GL_POLYGON);

    for (auto point : tip)
    {
        glVertex3f(point[0], point[1], point[2]);
    }

    glEnd();

    glFlush();

    glutTimerFunc(200, func, 0);
}

void myDisplay()
{
    func(0);
}

int main(int argc, char *argv[])
{

    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
    glutInitWindowSize(640, 480);
    glutCreateWindow("Output Screen");
    glutDisplayFunc(myDisplay);
    myInit();
    glutMainLoop();

    return 0;
}