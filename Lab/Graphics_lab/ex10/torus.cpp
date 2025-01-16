#include <GL/glut.h>
#include <stdlib.h>

GLfloat T = 0;

void Spin()
{
    T = T + 1;
    if(T > 360)
        T = 0;
    glutPostRedisplay();
}

static void resize(int width, int height)
{
    const float ar = (float)width / (float)height;

    glViewport(0, 0, width, height);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    glFrustum(-ar, ar, -1.0, 1.0, 2.0, 100.0);

    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();
}

static void display(void)
{
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    glColor3d(1, 0, 0);

    glLoadIdentity();
    gluLookAt(0, 1, 3, 0, 0, 0, 0, 1, 0);

    glPushMatrix();
    glTranslated(0.0, 1.5, -6);
    glRotated(-10, 1.0, 0.0, 0.0);
    glRotatef(T, 0, 1, 0); // Rotate around the y-axis (change this if needed)
    glutSolidTorus(0.4, 0.8, 10, 50);
    glPopMatrix();

    glPushMatrix();
    glTranslated(0.0, -1.2, -6);
    glRotatef(T, 0, 1, 0); // Rotate around the y-axis (change this if needed)
    glutWireTorus(0.4, 0.8, 10, 20);
    glPopMatrix();

    glutSwapBuffers();
}

const GLfloat light_diffuse[] = {1.0f, 1.0f, 1.0f, 1.0f};
const GLfloat light_position[] = {2.0f, 5.0f, 5.0f, 0.0f};

int main(int argc, char *argv[])
{
    glutInit(&argc, argv);
    glutInitWindowSize(640, 480);
    glutInitWindowPosition(10, 10);
    glutInitDisplayMode(GLUT_RGB | GLUT_DOUBLE | GLUT_DEPTH);

    glutCreateWindow("Programming Techniques - 3D Torus");

    glutReshapeFunc(resize);
    glutDisplayFunc(display);
    glutIdleFunc(Spin);

    glClearColor(1, 1, 1, 1);
    glEnable(GL_CULL_FACE);
    glCullFace(GL_BACK);

    glEnable(GL_DEPTH_TEST);
    glDepthFunc(GL_LESS);

    glEnable(GL_LIGHT0);
    glEnable(GL_LIGHTING);

    glLightfv(GL_LIGHT0, GL_DIFFUSE, light_diffuse);
    glLightfv(GL_LIGHT0, GL_POSITION, light_position);

    glutMainLoop();

    return 0;
}
