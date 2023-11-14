






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

