class Col {
	private int c;

	public Col(int c) {
		this.c = c;
	}
	public Col(String s) {
		switch(s) {
		case "blue":
			c = 0;
      	  	break; 
        case "orange":
			c = 1;
	        break; 
        case "purple":
			c = 2;
	      	break; 
        case "yellow":
			c = 3;
	      	break; 
        case "green":
        	c = 4;
        	break;
		}
	}
	
	public int getInt() {
		return c;
	}
	public String toString() {
		switch(c) {
		case 0:
			return "blue";
        case 1:
        	return "orange"; 
        case 2:
        	return "purple";
        case 3:
			return "yellow";
        case 4:
			return "green";
		}
		return "none";
	}
	
	static final Col BLUE = new Col(0);
	static final Col ORANGE = new Col(1);
	static final Col PURPLE = new Col(2);
	static final Col YELLOW = new Col(3);
	static final Col GREEN = new Col(4);
}