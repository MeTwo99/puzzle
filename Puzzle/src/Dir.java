class Dir {
	private int d;
	public Dir(int d) {
		this.d = d;
	}
	public Dir(String s) {
		switch(s) {
		case "down":
			d = 0;
      	  	break; 
        case "up":
			d = 1;
	        break; 
        case "left":
			d = 2;
	      	break; 
        case "right":
			d = 3;
	      	break; 
		}
	}
	public int getInt() {
		return d;
	}
	public String toString() {
		switch(d) {
		case 0:
			return "down";
        case 1:
        	return "up"; 
        case 2:
        	return "left";
        case 3:
			return "right";
		}
		return "none";
	}
	
	
	static final Dir DOWN = new Dir(0);
	static final Dir UP = new Dir(1);
	static final Dir LEFT = new Dir(2);
	static final Dir RIGHT = new Dir(3);
}