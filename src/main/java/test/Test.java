/**
 * 
 */
package test;

import entity.Alarm;
import util.ModbusUtils;
import util.TypeConversion;

/**
 * @author wangxinyu
 *
 */
public class Test {
	
	private static final int PORT = 502;
	private static final String IP = "10.211.55.3";//Your IP Address
	private static final int LENGTH = 1;
	private static final int ADDRESS = 0;
	private static final int index[]={0,1,2};
	private static final int SLAVE_ID = 1;
	
	public static void main(String[] args) {
		ModbusUtils utils = new ModbusUtils(IP, PORT); 
		//按下标取出
//		for(int i = 0; i < index.length; i++){
//			System.out.println(i + ":" + utils.readInputRegister(ADDRESS, SLAVE_ID, LENGTH, i));
//		}
		//全部取出
//		int[] data = utils.readInputRegister(ADDRESS, SLAVE_ID, LENGTH);
//		for(int i = 0; i < data.length; i++){
//			System.out.print(data[i] + "  ");
//		}
//		System.out.println();
		//utils.writeBooleanValueRegister(ADDRESS, SLAVE_ID, false);
		//写入单个值
//		utils.writeValueRegister(ADDRESS, SLAVE_ID, 126);
		//写入多个值
//		int[] values = {3,4};
//		utils.writeMultiValueRegister(ADDRESS, SLAVE_ID, values);
		//写入多个字符
		Alarm a = new Alarm();
		String hex = TypeConversion.string2HexString(a.toString());
		System.out.println(hex);
		System.out.println(TypeConversion.hexString2String(hex));
		utils.writeHexStringRegister(ADDRESS, SLAVE_ID, hex);
	}
}











