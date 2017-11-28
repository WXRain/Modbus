/**
 * 
 */
package entity;

/**
 * @author wangxinyu
 *
 */
public class Alarm {

	private long id;
	private long moId;
	private int enbTypeId;
	private String moName = "";
	private String entityType = "";
	private String entityOid = "";
	private long alarmDefId;
	private String alarmContent="";
	private int alarmLevel;
	private int alarmState;
	private long firstAlarmTime;
	private long lastAlarmTime;
	private long alarmTimes;
	private long restoredTime;
	private String restoreUser="";
	private int restoreFlag;
	private int alarmType;
	private int confirmState;
	private String confirmUser="";
	private long confirmTime;
	private long serialNum;
	private int alarmClass;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getMoId() {
		return moId;
	}
	public void setMoId(long moId) {
		this.moId = moId;
	}
	public int getEnbTypeId() {
		return enbTypeId;
	}
	public void setEnbTypeId(int enbTypeId) {
		this.enbTypeId = enbTypeId;
	}
	public String getMoName() {
		return moName;
	}
	public void setMoName(String moName) {
		this.moName = moName;
	}
	public String getEntityType() {
		return entityType;
	}
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	public String getEntityOid() {
		return entityOid;
	}
	public void setEntityOid(String entityOid) {
		this.entityOid = entityOid;
	}
	public long getAlarmDefId() {
		return alarmDefId;
	}
	public void setAlarmDefId(long alarmDefId) {
		this.alarmDefId = alarmDefId;
	}
	public String getAlarmContent() {
		return alarmContent;
	}
	public void setAlarmContent(String alarmContent) {
		this.alarmContent = alarmContent;
	}
	public int getAlarmLevel() {
		return alarmLevel;
	}
	public void setAlarmLevel(int alarmLevel) {
		this.alarmLevel = alarmLevel;
	}
	public int getAlarmState() {
		return alarmState;
	}
	public void setAlarmState(int alarmState) {
		this.alarmState = alarmState;
	}
	public long getFirstAlarmTime() {
		return firstAlarmTime;
	}
	public void setFirstAlarmTime(long firstAlarmTime) {
		this.firstAlarmTime = firstAlarmTime;
	}
	public long getLastAlarmTime() {
		return lastAlarmTime;
	}
	public void setLastAlarmTime(long lastAlarmTime) {
		this.lastAlarmTime = lastAlarmTime;
	}
	public long getAlarmTimes() {
		return alarmTimes;
	}
	public void setAlarmTimes(long alarmTimes) {
		this.alarmTimes = alarmTimes;
	}
	public long getRestoredTime() {
		return restoredTime;
	}
	public void setRestoredTime(long restoredTime) {
		this.restoredTime = restoredTime;
	}
	public String getRestoreUser() {
		return restoreUser;
	}
	public void setRestoreUser(String restoreUser) {
		this.restoreUser = restoreUser;
	}
	public int getRestoreFlag() {
		return restoreFlag;
	}
	public void setRestoreFlag(int restoreFlag) {
		this.restoreFlag = restoreFlag;
	}
	public int getAlarmType() {
		return alarmType;
	}
	public void setAlarmType(int alarmType) {
		this.alarmType = alarmType;
	}
	public int getConfirmState() {
		return confirmState;
	}
	public void setConfirmState(int confirmState) {
		this.confirmState = confirmState;
	}
	public String getConfirmUser() {
		return confirmUser;
	}
	public void setConfirmUser(String confirmUser) {
		this.confirmUser = confirmUser;
	}
	public long getConfirmTime() {
		return confirmTime;
	}
	public void setConfirmTime(long confirmTime) {
		this.confirmTime = confirmTime;
	}
	public long getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(long serialNum) {
		this.serialNum = serialNum;
	}
	public int getAlarmClass() {
		return alarmClass;
	}
	public void setAlarmClass(int alarmClass) {
		this.alarmClass = alarmClass;
	}
	@Override
	public String toString() {
		return "[id=" + id + ", moId=" + moId + ", enbTypeId=" + enbTypeId + ", moName=" + moName
				+ ", entityType=" + entityType + ", entityOid=" + entityOid + ", alarmDefId=" + alarmDefId
				+ ", alarmContent=" + alarmContent + ", alarmLevel=" + alarmLevel + ", alarmState=" + alarmState
				+ ", firstAlarmTime=" + firstAlarmTime + ", lastAlarmTime=" + lastAlarmTime + ", alarmTimes="
				+ alarmTimes + ", restoredTime=" + restoredTime + ", restoreUser=" + restoreUser + ", restoreFlag="
				+ restoreFlag + ", alarmType=" + alarmType + ", confirmState=" + confirmState + ", confirmUser="
				+ confirmUser + ", confirmTime=" + confirmTime + ", serialNum=" + serialNum + ", alarmClass="
				+ alarmClass + "]";
	}
	
	
}
