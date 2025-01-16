#include <GL/glut.h>

void drawAxes() {
    glBegin(GL_LINES);
    // X-axis
    glVertex2f(-1.5, 0.0);
    glVertex2f(1.5, 0.0);
    // Y-axis
    glVertex2f(0.0, -1.5);
    glVertex2f(0.0, 1.5);
    glEnd();
}

void drawTriangle() {
    glBegin(GL_TRIANGLES);
    glVertex2f(0.1, 0.1);
    glVertex2f(0.5, 0.1);
    glVertex2f(0.3, 0.5);
    glEnd();
}

void drawReflectionXAxis() {
    glPushMatrix();
    glScalef(1.0, -1.0, 1.0); // Apply reflection across the x-axis
    drawTriangle();
    glPopMatrix();
}

void drawReflectionYAxis() {
    glPushMatrix();
    glScalef(-1.0, 1.0, 1.0); // Apply reflection across the y-axis
    drawTriangle();
    glPopMatrix();
}

void drawReflectionDiagonal() {
    glPushMatrix();
    glScalef(-1.0, -1.0, 1.0); // Apply reflection across the line x=y
    drawTriangle();
    glPopMatrix();
}

void drawReflectionOrigin() {
    glPushMatrix();
    glScalef(-1.0, -1.0, 1.0); // Apply reflection across the origin
    drawTriangle();
    glPopMatrix();
}

void display() {
    glClear(GL_COLOR_BUFFER_BIT);

    glColor3f(1.0, 0.0, 0.0); // Red color for the original triangle
    drawAxes();
    drawTriangle();

    glColor3f(0.0, 0.0, 1.0); // Blue color for the reflected triangle
    // drawReflectionXAxis();
    // drawReflectionYAxis();
    // drawReflectionDiagonal();
    // drawReflectionOrigin();

    glutSwapBuffers();
}

void myInit() {
    glClearColor(1.0, 1.0, 1.0, 0.0); // Set background color to white
    glColor3f(0.0f, 0.0f, 0.0f); // Set drawing color to black
    glPointSize(4.0);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluOrtho2D(-1.5, 1.5, -1.5, 1.5);
}

int main(int argc, char *argv[]) {
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB);
    glutCreateWindow("Triangle with Reflection");
    glutDisplayFunc(display);
    myInit();
    glutMainLoop();
    return 0;
}
