package org.invik.corsaircuejna.jna;
import org.bridj.BridJ;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

/**
 * contains number of leds and arrays with their positions<br>
 * <i>native declaration : include\CUESDK.h:91</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("CorsairCUEJNAWrapper.x64_2013")
public class JNACorsairLedPositions extends StructObject {
	static {
		BridJ.register();
	}
	/** integer value.Number of elements in following array */
	@Field(0) 
	public int numberOfLed() {
		return this.io.getIntField(this, 0);
	}
	/** integer value.Number of elements in following array */
	@Field(0) 
	public JNACorsairLedPositions numberOfLed(int numberOfLed) {
		this.io.setIntField(this, 0, numberOfLed);
		return this;
	}
	/**
	 * array of led positions<br>
	 * C type : JNACorsairLedPosition*
	 */
	@Field(1) 
	public Pointer<JNACorsairLedPosition> pLedPosition() {
		return this.io.getPointerField(this, 1);
	}
	/**
	 * array of led positions<br>
	 * C type : JNACorsairLedPosition*
	 */
	@Field(1) 
	public JNACorsairLedPositions pLedPosition(Pointer<JNACorsairLedPosition> pLedPosition) {
		this.io.setPointerField(this, 1, pLedPosition);
		return this;
	}
	public JNACorsairLedPositions() {
		super();
	}
	public JNACorsairLedPositions(Pointer pointer) {
		super(pointer);
	}
}
