public Game() {
    addImages();
    initializeVariables();
    addKeyListener(new TAdapter());
    setFocusable(true);
    initializeGame();
}
