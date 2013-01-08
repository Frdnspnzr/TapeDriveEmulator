package de.frdnspnzr.tapeDriveEmulator;

public class TapeDrive {

	private Tape tape;
	private int position;
	private Mode mode;
	
	private enum Mode {
		write,read;
	}

	public void loadTape(Tape tape) throws TapeDriveOccupiedException {
		if (this.tape != null)
			throw new TapeDriveOccupiedException();
		else
			this.tape = tape;
	}

	public void unloadTape() throws NoTapeLoadedException {

		if (this.tape == null)
			throw new NoTapeLoadedException();
		else
			this.tape = null;
	}

	public void rewind() throws NoTapeLoadedException {
		if (this.tape == null)
			throw new NoTapeLoadedException();
		else {
			this.position = 0;
			this.mode = Mode.read;
		}
	}
	
	public void rewrite() throws NoTapeLoadedException {
		if (this.tape == null)
			throw new NoTapeLoadedException();
		else {
			this.position = 0;
			this.mode = Mode.write;
		}
	}
	
	public Object read() throws OperationNotAllowedException, TapeHasEndedException {
		if (this.mode != Mode.read)
			throw new OperationNotAllowedException();
		else if (this.position >= this.tape.getLength())
			throw new TapeHasEndedException();
		else {
			Object o = this.tape.getValue(this.position);
			this.position++;
			return o;
		}
	}
	
	public void write(Object o) throws OperationNotAllowedException, TapeHasEndedException {
		if (this.mode != Mode.write)
			throw new OperationNotAllowedException();
		else if (this.position >= this.tape.getLength())
			throw new TapeHasEndedException();
		else {
			this.tape.setValue(this.position, (Object) o);
			this.position++;
		}
	}
	
}
