package de.frdnspnzr.tapeDriveEmulator.tests;

import de.frdnspnzr.tapeDriveEmulator.NoTapeLoadedException;
import de.frdnspnzr.tapeDriveEmulator.Tape;
import de.frdnspnzr.tapeDriveEmulator.TapeDrive;
import de.frdnspnzr.tapeDriveEmulator.TapeDriveException;
import de.frdnspnzr.tapeDriveEmulator.TapeDriveOccupiedException;
import de.frdnspnzr.tapeDriveEmulator.TapeHasEndedException;

public class TapeDriveTester {

	public static void main(String[] args) {

		TapeDrive drive1 = new TapeDrive();
		TapeDrive drive2 = new TapeDrive();
		Tape<Integer> tape1 = new Tape<Integer>(20);
		Tape<String> tape2 = new Tape<String>(10);

		//First we write some integers to our Intenger-tape
		try {
			drive1.loadTape(tape1);
		} catch (TapeDriveOccupiedException e) {
			//That is pretty much unexpected
			e.printStackTrace();
		}

		try {
			drive1.rewrite();
		} catch (NoTapeLoadedException e) {
			//That should not happen, too
			e.printStackTrace();
		}

		for (int i = 1; i <= 15; i++) {
			try {
				drive1.write(i);
			} catch (TapeDriveException e) {
				//Should not happen, either. Tape drives are hard!
				e.printStackTrace();
			}
		}

		//Now, let's read them again
		try {
			drive1.rewind();

			//Yep, we get some Object. That's how tape drives work.
			//You never know what's in there!
			Integer value = (Integer) drive1.read();
			while (value != null) {
				System.out.println("Read " + value + " from tape drive.");
				value = (Integer) drive1.read();
			}

		} catch (TapeDriveException e) {
			//None of this should happen!
			e.printStackTrace();
		}

		//Let's convert these Integers to Strings and put them on tape 2
		try {
			drive2.loadTape(tape2);

			drive1.rewind();
			drive2.rewrite();

			Integer value = (Integer) drive1.read();
			while (value != null) {
				drive2.write(new String("Tape 1 had " + value));
				value = (Integer) drive1.read();
			}

		} catch (TapeDriveException e) {
			//This time an error should occur, bevause tape1 is longer than tape2
			e.printStackTrace();
		}

		//So, what's on drive2?
		try {

			drive2.rewind();

			//This time, we read until the tape ends
			while (true) {
				String value = (String) drive2.read();
				System.out.println(value);
			}

		} catch (TapeHasEndedException e) {
			//That's expected, don't bother
		} catch (TapeDriveException e) {
			//Again, nothing should happen
			e.printStackTrace();
		}
	}

}
