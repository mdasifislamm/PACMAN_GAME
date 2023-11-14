






public Game() {
    addImages();
    initializeVariables();
    addKeyListener(new TAdapter());
    setFocusable(true);
    initializeGame();
}



    private void addImages() {
    	down = new ImageIcon("res/down.gif").getImage();
    	up = new ImageIcon("res/up.gif").getImage();
    	left = new ImageIcon("res/left.gif").getImage();
    	right = new ImageIcon("res/right.gif").getImage();
        ghost = new ImageIcon("res/ghost.gif").getImage();
        heart = new ImageIcon("res/heart.png").getImage();

    }
private void initializeVariables() {

    screenData = new int[NUM_BLOCK * NUM_BLOCK];
    dim = new Dimension(400, 400);
    ghostX = new int[MAX_GHOSTS];
    ghostDX = new int[MAX_GHOSTS];
    ghostY = new int[MAX_GHOSTS];
    ghostDY = new int[MAX_GHOSTS];
    ghostSpeed = new int[MAX_GHOSTS];
    dx = new int[4];
    dy = new int[4];

    timer = new Timer(40, this);
    timer.start();
}

private void startGame(Graphics2D g) {

    if (pacmanAlive) {

        death();

    } else {

        movePacman();
        drawPacman(g);
        moveGhosts(g);
        checkMaze();
    }
}


private void showIntro(Graphics2D g2d) {
 
    	String start = "Press ENTER to start";
        g2d.setColor(Color.yellow);
        g2d.drawString(start, 70, 180);
    }

    private void drawScore(Graphics2D g) {
        g.setFont(font);
        g.setColor(new Color(124, 211, 9));
        String s = "Score: " + score;
        g.drawString(s,  247,  376);

        for (int i = 0; i < lives; i++) {
            g.drawImage(heart, i * 28 + 8, SCREEN_SIZE + 1, this);
        }
    }
	
	private void checkMaze() {

        int i = 0;
        boolean finished = true;

        while (i < NUM_BLOCK * NUM_BLOCK && finished) {

            if ((screenData[i]) != 0) {
                finished = false;
            }

            i++;
        }

        if (finished) {

            score += 50;

            if (N_GHOSTS < MAX_GHOSTS) {
                N_GHOSTS++;
            }

            if (currentSpeed < maxSpeed) {
                currentSpeed++;
            }

            initializeLevel();
        }
    }
private void death() {

    lives--;

    if (lives == 0) {
        gameRunning = false;
    }

    continueLevel();
}

private void moveGhosts(Graphics2D g) {

        int pos;
        int count;

        for (int i = 0; i < N_GHOSTS; i++) {
            if (ghostX[i] % B_SIZE == 0 && ghostY[i] % B_SIZE == 0) {
                pos = ghostX[i] / B_SIZE + NUM_BLOCK * (int) (ghostY[i] / B_SIZE);

                count = 0;

                if ((screenData[pos] & 1) == 0 && ghostDX[i] != 1) {
                    dx[count] = -1;
                    dy[count] = 0;
                    count++;
                }

                if ((screenData[pos] & 2) == 0 && ghostDY[i] != 1) {
                    dx[count] = 0;
                    dy[count] = -1;
                    count++;
                }

                if ((screenData[pos] & 4) == 0 && ghostDX[i] != -1) {
                    dx[count] = 1;
                    dy[count] = 0;
                    count++;
                }

                if ((screenData[pos] & 8) == 0 && ghostDY[i] != -1) {
                    dx[count] = 0;
                    dy[count] = 1;
                    count++;
                }

                if (count == 0) {

                    if ((screenData[pos] & 15) == 15) {
                        ghostDX[i] = 0;
                        ghostDY[i] = 0;
                    } else {
                        ghostDX[i] = -ghostDX[i];
                        ghostDY[i] = -ghostDY[i];
                    }

                } else {

                    count = (int) (Math.random() * count);

                    if (count > 3) {
                        count = 3;
                    }

                    ghostDX[i] = dx[count];
                    ghostDY[i] = dy[count];
                }

            }

            ghostX[i] = ghostX[i] + (ghostDX[i] * ghostSpeed[i]);
            ghostY[i] = ghostY[i] + (ghostDY[i] * ghostSpeed[i]);
            drawGhost(g, ghostX[i] + 1, ghostY[i] + 1);

            if (pacmanX > (ghostX[i] - 12) && pacmanX < (ghostX[i] + 12)
                    && pacmanY > (ghostY[i] - 12) && pacmanY < (ghostY[i] + 12)
                    && gameRunning) {

                pacmanAlive = true;
            }
        }
    }

    private void drawGhost(Graphics2D g, int x, int y) {
    	g.drawImage(ghost, x, y, this);
        }

