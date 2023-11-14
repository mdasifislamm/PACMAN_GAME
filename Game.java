






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