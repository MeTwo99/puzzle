class Dir {
	private int d;
	private boolean on;
	public Dir(int d, boolean on) {
		this.d = d;
		this.on = on;
	}
	public Dir(int d) {
		this.d = d;
		on = false;
	}
	public int getInt() {
		return d;
	}
	public boolean isOn() {
		return on;
	}
	
	static final Dir DOWN = new Dir(0);
	static final Dir UP = new Dir(1);
	static final Dir LEFT = new Dir(2);
	static final Dir RIGHT = new Dir(3);
}