/**
 * 
 */
package test;

import java.net.InetAddress;

import net.wimpi.modbus.io.ModbusTCPTransaction;
import net.wimpi.modbus.msg.ReadInputRegistersRequest;
import net.wimpi.modbus.msg.ReadInputRegistersResponse;
import net.wimpi.modbus.msg.WriteCoilRequest;
import net.wimpi.modbus.msg.WriteMultipleRegistersRequest;
import net.wimpi.modbus.msg.WriteSingleRegisterRequest;
import net.wimpi.modbus.net.TCPMasterConnection;
import net.wimpi.modbus.procimg.Register;
import net.wimpi.modbus.procimg.SimpleRegister;

/**
 * @author wangxinyu
 *
 */
public class ModbusUtils {
	
	private TCPMasterConnection con;
	
	//初始化时建立连接
	public ModbusUtils(String ip, int port){
		try{
			InetAddress adr = InetAddress.getByName(ip);
			con = new TCPMasterConnection(adr);
			con.setPort(port);
			con.connect();
		}catch(Exception e){
			System.out.println("连接过程出错！");
			e.printStackTrace();
		}
	}
	
	//从给定下标中读出值
	public int readInputRegister(int address, int slaveId, int length, int index){
		int data = 0;
		try{			
			ReadInputRegistersRequest req = new ReadInputRegistersRequest(address, length);
			req.setUnitID(slaveId);
			
			ModbusTCPTransaction trans = new ModbusTCPTransaction(con);
			trans.setRequest(req);
			trans.execute();
			
			ReadInputRegistersResponse res = (ReadInputRegistersResponse)trans.getResponse();
			
			data = res.getRegisterValue(index);
			
			disConnect(con);
		}catch(Exception e){
			System.out.println("读取" + index + "出错了哦！");
			e.printStackTrace();
		}
		return data;
	}
	
	//读出给定长度的值
	public int[] readInputRegister(int address, int slaveId, int length){
		int[] data = new int[length];
		try{
			for(int i = 0; i < length; i++){
				data[i] = readInputRegister(address, slaveId, length, i);
			}
		}catch(Exception e){
			System.out.println("一次读取" + length + "出错了哦！");
			e.printStackTrace();
		}
		return data;
	}
	
	//写入布尔类型值
	public void writeBooleanValueRegister(int address, int slaveId, boolean value){
		try{
			
			WriteCoilRequest req = new WriteCoilRequest(address, value);
			req.setUnitID(slaveId);
			
			ModbusTCPTransaction trans = new ModbusTCPTransaction(con);
			trans.setRequest(req);
			trans.execute();
			
			disConnect(con);
			
			System.out.println("写入成功！");
			
		}catch(Exception e){
			System.out.println("写入address:" + address + " " + "slaveId:" + slaveId + " " + "value:" + value + " 出错了哦！");
			e.printStackTrace();
		}
	}
	
	//写入单个数值
	public void writeValueRegister(int address, int slaveId, int value){
		try{
			Register register = new SimpleRegister();
			register.setValue(value);
			WriteSingleRegisterRequest req = new WriteSingleRegisterRequest(address, register);
			req.setUnitID(slaveId);
			
			ModbusTCPTransaction trans = new ModbusTCPTransaction(con);
			trans.setRequest(req);
			trans.execute();
			
			disConnect(con);
			
			System.out.println("写入成功！");
		}catch(Exception e){
			System.out.println("写入address:" + address + " " + "slaveId:" + slaveId + " " + "value:" + value + " 出错了哦！");
			e.printStackTrace();
		}
	}
	
	//写入多个数值
	public void writeMultiValueRegister(int address, int slaveId, int[] values){
		Register[] registers = new SimpleRegister[values.length];
		try{
			for(int i = 0; i < values.length; i++){
				Register register = new SimpleRegister();
				register.setValue(values[i]);
				registers[i] = register;
			}
			
			WriteMultipleRegistersRequest req = new WriteMultipleRegistersRequest(address, registers);
			req.setUnitID(slaveId);
			
			ModbusTCPTransaction trans = new ModbusTCPTransaction(con);
			trans.setRequest(req);
			trans.execute();
			
			disConnect(con);
			
			System.out.println("写入成功！");
		}catch(Exception e){
			System.out.println("写入address:" + address + " " + "slaveId:" + slaveId + " " + "value:" + values + " 出错了哦！");
			e.printStackTrace();
		}
	}
	
	//断开连接
	public void disConnect(TCPMasterConnection con){
		con.close();
	}
}
