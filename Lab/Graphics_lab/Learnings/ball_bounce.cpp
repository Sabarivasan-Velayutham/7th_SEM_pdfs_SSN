#include <GL/glut.h>

float sphereY = 3.0; // Initial y-coordinate of the sphere
int direction = 1;   // 1 for moving down, -1 for moving up
int bounceCount = 0;  // Number of bounces

void myInit() {
    glClearColor(1.0, 1.0, 1.0, 0.0);
    glColor3f(0.0, 0.0, 0.0);
    glPointSize(2.0);
    gluOrtho2D(-5.0, 10.0, -5.0, 10.0); // Set the viewing area
}

void drawSphere() {
    glPushMatrix();
    glTranslatef(0.0, -sphereY, 0.0); // Translate the sphere along the y-axis
    glutWireSphere(2.0, 20, 20);
    glPopMatrix();
}

void myDisplay() {
    glClear(GL_COLOR_BUFFER_BIT);

    drawSphere();

    // Update the y-coordinate for the animation
    if (direction == 1) {
        if (sphereY > 0.0) {
            sphereY -= 0.02; // Adjust the speed of the animation as needed
        } else {
            direction = -1;
            bounceCount++;
        }
    } else {
        if (bounceCount < 2) {
            if (sphereY < 5.0) {
                sphereY += 0.02; // Adjust the speed of the animation as needed
            } else {
                direction = 1;
            }
        }
    }

    glutSwapBuffers();
    glutPostRedisplay(); // Request a redraw to create animation effect
}

int main(int argc, char** argv) {
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB);
    glutInitWindowSize(500, 500);
    glutInitWindowPosition(100, 150);
    glutCreateWindow("Bouncing Sphere");
    glutDisplayFunc(myDisplay);
    myInit();
    glutMainLoop();
    return 0;
}
