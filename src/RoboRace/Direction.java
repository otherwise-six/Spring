package RoboRace;

public enum Direction {
	North {
                @Override
		public Direction rotate(boolean clockwise) {
			if (clockwise) return East;
			return West;
		}
	},
	East {
                @Override
		public Direction rotate(boolean clockwise) {
			if (clockwise) return South;
			return North;
		}
	},
	South {
                @Override
		public Direction rotate(boolean clockwise) {
			if (clockwise) return West;
			return East;
		}
	},
	West {
                @Override
		public Direction rotate(boolean clockwise) {
			if (clockwise) return North;
			return South;
		}
	};
	
	public abstract Direction rotate(boolean clockwise);
	
	public Direction halfturn() {
		return rotate(true).rotate(true);
	}
}