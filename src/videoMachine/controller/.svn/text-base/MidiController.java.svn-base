package videoMachine.controller;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiDevice.Info;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Transmitter;

import videoMachine.midi.DumpReceiver;


public class MidiController{

	private MidiDevice _currentDevice;
	private MainController _mainController;
	
	public MidiController(MainController mainController) {
		_mainController = mainController;
	}
	
	/**
	 * Get a list of the available devices
	 * @param bForInput		Get input devices?
	 * @param bForOutput	Get output devices?
	 * @return	An array of Info objects for available MIDI devices
	 */
	public Info[] listDevices(boolean bForInput, boolean bForOutput) {
		Info[] devices = new Info[0];
		
		Info[] availDevices = MidiSystem.getMidiDeviceInfo();
		for(int i = 0; i < availDevices.length; i++)
		{
			Info deviceInfo = availDevices[i];
			try {
				MidiDevice device = MidiSystem.getMidiDevice(deviceInfo);
				Boolean bAllowsInput = (device.getMaxTransmitters() != 0);
				Boolean bAllowsOutput = (device.getMaxReceivers() != 0);
				
				if ((bAllowsInput && bForInput) ||
					 (bAllowsOutput && bForOutput))
				{
					devices = expandArray(devices, devices.length + 1);
					devices[devices.length - 1] = availDevices[i];
				}
			} catch (MidiUnavailableException e) {
				e.printStackTrace();
			}
		}
		
		return devices;
	}
	
	/**
	 * Expand the size of an array of Info objects
	 * @param origArray	The array to expand
	 * @param size The size to expand to
	 * @return The expanded array containing the original data
	 */
	private Info[] expandArray(Info[] origArray, int size)
	{
		Info[] temp = new Info[size];
		System.arraycopy(origArray, 0, temp, 0, origArray.length);
		return temp;
	}
	
	public boolean connectToDevice(Info device){
		boolean isConnected = true;
		
		if (_currentDevice != null && _currentDevice.isOpen())
			_currentDevice.close();
		
		try{
			_currentDevice = MidiSystem.getMidiDevice(device);
			_currentDevice.open();
		}
		catch (MidiUnavailableException e){
			System.out.println(e);
			isConnected = false;
		}
		
		if (_currentDevice == null){
			System.out.println("wasn't able to retrieve MidiDevice");
			isConnected = false;
		}
		
		Receiver r = new DumpReceiver(System.out);
		((DumpReceiver)r).addMidiListener(_mainController);
		try{
			Transmitter	t = _currentDevice.getTransmitter();
			t.setReceiver(r);
		}
		catch (MidiUnavailableException e){
			System.out.println("wasn't able to connect the device's Transmitter to the Receiver:");
			System.out.println(e); 
			_currentDevice.close();
			isConnected = false;
		}
		
		System.out.println("Now using: " + device.getName());
		
		return isConnected;
	}
	
	public void disconnectDevice(){
		if (_currentDevice.isOpen()) {
			_currentDevice.close();
			System.out.println("Device Closed");
		}
	}

}
