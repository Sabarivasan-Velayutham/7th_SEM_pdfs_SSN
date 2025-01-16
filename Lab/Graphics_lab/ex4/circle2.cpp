

#include <GL/glut.h>
#include <stdio.h>
#include <math.h>

int xstart, ystart, xend, yend, xc, yc, radius;

void myInit()
{
    glClear(GL_COLOR_BUFFER_BIT);
    glClearColor(0.0, 0.0, 0.0, 1.0);
    glMatrixMode(GL_PROJECTION);
    gluOrtho2D(-320, 320, -200, 200);
}

void draw_pixel(int x, int y)
{
    glBegin(GL_POINTS);
    glVertex2i(x, y);
    glEnd();
}

void draw_line(int xstart, int xend, int ystart, int yend)
{
    int dx, dy, i, e;
    int incx, incy, inc1, inc2;
    int x, y;
    dx = abs(xend - xstart);
    dy = abs(yend - ystart);
    incx = 1;
    if (xend < xstart)
        incx = -1;
    incy = 1;
    if (yend < ystart)
        incy = -1;
    x = xstart;
    y = ystart;
    if (dx > dy)
    {
        draw_pixel(x, y);
        e = 2 * dy - dx;
        inc1 = 2 * (dy - dx);
        inc2 = 2 * dy;
        for (i = 0; i < dx; i++)
        {
            if (e >= 0)
            {
                y += incy;
                e += inc1;
            }
            else
                e += inc2;
            x += incx;
            draw_pixel(x, y);
        }
    }
    else
    {
        draw_pixel(x, y);
        e = 2 * dx - dy;
        inc1 = 2 * (dx - dy);
        inc2 = 2 * dx;
        for (i = 0; i < dy; i++)
        {
            if (e >= 0)
            {
                x += incx;
                e += inc1;
            }
            else
                e += inc2;
            y += incy;
            draw_pixel(x, y);
        }
    }
}

void comb(int x, int y, int xc, int yc)
{ // x += xc;
    // y += yc;
    draw_pixel(x + xc, y + yc);
    draw_pixel(y + xc, x + yc);
    draw_pixel(-x + xc, y + yc);
    draw_pixel(-y + xc, x + yc);
    draw_pixel(-x + xc, -y + yc);
    draw_pixel(-y + xc, -x + yc);
    draw_pixel(x + xc, -y + yc);
    draw_pixel(y + xc, -x + yc);
}

void draw_circle(int xc, int yc, int radius)
{
    int p0 = 1 - radius;
    int xstart = 0;
    int ystart = radius;
    comb(xstart, ystart, xc, yc);
    while (xstart < ystart)
    {
        xstart += 1;
        int pnew = p0 + 2 * xstart + 1;
        if (p0 > 0)
        {
            ystart -= 1;
            pnew -= 2 * ystart;
        }
        comb(xstart, ystart, xc, yc);
        p0 = pnew;
    }
}

void myDisplay()
{
    // draw_line(xstart, xend, ystart, yend); draw_line(-1000, 1000,0, 0);
    draw_line(0, 0, -1000, 1000);
    draw_circle(xc, yc, radius);
    draw_line(radius, 0, 0, radius);
    draw_line(-radius, 0, 0, radius);
    draw_line(radius, 0, 0, -radius);
    draw_line(-radius, 0, 0, -radius);
    glFlush();
}

int main(int argc, char **argv)
{
    // printf( "Enter (xstart, ystart, xend, yend)\n"); // scanf("%d %d %d %d", &xstart, &ystart, &xend, &yend);
    printf("Enter xc, yc and radius\n");
    scanf("%d %d %d", &xc, &yc, &radius);
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
    glutInitWindowSize(500, 500);
    glutInitWindowPosition(0, 0);
    glutCreateWindow("Excercise 4");
    myInit();
    glutDisplayFunc(myDisplay);
    glutMainLoop();
}