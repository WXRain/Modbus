/**
 * 
 */
package util;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;

import net.wimpi.modbus.io.ModbusTCPTransaction;
import net.wimpi.modbus.msg.ReadInputRegistersRequest;
import net.wimpi.modbus.msg.ReadInputRegistersResponse;
import net.wimpi.modbus.msg.WriteCoilRequest;
import net.wimpi.modbus.msg.WriteMultipleCoilsRequest;
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
	
	private static final int MAX_LENGTH = 255;//字节
	
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
	
	//写入多个字符值
	public void writeMultiBytesValueRegister(int address, int slaveId, byte[] values){
		try{
			Register register = new SimpleRegister();

			WriteSingleRegisterRequest req = new WriteSingleRegisterRequest(address, register);
			req.setUnitID(slaveId);
			
			OutputStream os = new ByteArrayOutputStream();
			os.write(values);
			System.out.println(values.length);
			DataOutputStream dos = new DataOutputStream(os);
			req.writeData(dos);
			
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
	
	public void writeHexStringRegister(int address, int slaveId, String hex){
		if(hex == null || hex.equals("")){
			System.out.println("字符串为空！");
		}
		try{
			String[] stringArray = spiltStringByLength(hex, MAX_LENGTH);
			
			for(int i = 0; i < stringArray.length; i++){
				System.out.println(stringArray[i]);
			}
			
			//强行将十六进制字符串拼成4的倍数字节
			int length = hex.length();
			for(int i = 0; i < (length%4==0?0:(4-length%4)); i++){
				hex += "0";
			}
			int[] values = new int[hex.length()/4];
			for(int i = 0; i < hex.length(); i+=4){
				String partHex = hex.substring(i, i+4);
				values[i/4] = Integer.parseInt(partHex, 16);
			}
			
			//writeMultiValueRegister(address, slaveId, values);
		}catch(Exception e){
			System.out.println("发送" + hex + "出错！");
			e.printStackTrace();
		}
	}
	
	public String[] spiltStringByLength(String hex, int maxLength){
		int index = 0;
		maxLength *= 2;
		String[] result = new String[hex.length()/maxLength + 1];
		while(hex.length() > maxLength){
			result[index] = hex.substring(0, maxLength);
			hex = hex.substring(maxLength);
			index++;
		}
		result[result.length - 1] = hex;
		return result;
	}
	
	//断开连接
	public void disConnect(TCPMasterConnection con){
		con.close();
	}
}
