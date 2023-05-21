package application;


public class HashTable {
	 static int tableSize;
	Students[] tableData;
	private int count;

	public HashTable(int n) {
		tableSize = n; // typically double the size and prime
		tableData = new Students[tableSize];
	}

	private int hash(int key, int step) {
		key = (key & 0x7fffffff) % tableSize;
		if (step == 0)
			return key;
		return (key + step * step) % tableSize; // Quadratic Probing
	}

	public void reHash() {
		count = 0;
		Students[] tableData2;
		tableData2 = tableData;
		tableSize = firstPrime(tableSize);
		tableData = new Students[tableSize];
		for (int i = 0; i < tableData2.length; i++) {
			if (tableData2[i] != null)
				addRecord(tableData2[i]);
		}

	}
	public boolean check() {
		double counter=0;
		for(int i=0;i<tableData.length;i++) {
			if(tableData[i]==null) {
				continue;
				
			}
			else {
				counter++;
			}
		}
		
		double y=counter/tableData.length;
		
		if(y>.7) {
			reHash();
			return true;
		}
		else {
			return false;
		}
	}
	public int findSize() {
		int counter=0;
		for(int i=0;i<tableData.length;i++) {
			if(tableData[i]==null) {
				continue;
			}
			else {
				counter++;
			}
		}
		return counter;
	}

	public boolean isEmpty(int slot) {
		return tableData[slot] == null;
	}

	public void addRecord(Students rec) {
		int keyCode = rec.hashCode();
		int step = 0;
		int slot = hash(keyCode, step);
		while (!isEmpty(slot) ) {
			slot = hash(keyCode, ++step);
		}
		tableData[slot] = rec;
		count++;
	}
		
	public String serch(String name) {
		for(int i=0;i<tableData.length;i++) {
			if(tableData[i]==null) {
				continue;
			}
			else {
				if(tableData[i].getFullName().equals(name)) {
					return tableData[i].toString();
				}
			}
		}
		return null;
	}

	public void Delete(int ID) {
		int step = 0;
		int slot = hash(ID, step);
		while (tableData[slot].getStudentId()!= ID)
			slot = hash(ID, ++step);
	

	}
	public int findINDexName(String name) {
		int j=-1;
		for(int i=0;i<tableData.length;i++) {
			if(tableData[i]==null) {
				continue;
			}
			else {
				if(tableData[i].getFullName().equals(name)) {
					j=i;
				}
			}
		}
		return j;
	}

	public int firstPrime(int min) {
		min = min * 2;
		for (int j = min + 1; true; j++)
			if (isPrime(j))
				return j;
	}

	private boolean isPrime(int n) {
		for (int j = 2; (j * j <= n); j++)
			if (n % j == 0)
				return false;
		return true;
	}
	

	

	public String printAll() {
		String lai="";
		
		System.out.println("la+i");
		for(int i=0;i<tableData.length;i++) {
		if(tableData[i]==null) {
			lai+=null+"\n";
			continue;
		}
		else {
			lai+=tableData[i].toString()+"\n";
		}
		}	
		return lai;
	}
	public void sort() {
		for(int i=0;i<tableData.length-1;i++) {
			if(tableData[i]==null) {
				continue;
			}
			else {
				for(int j=i+1;j<tableData.length;j++) {
					if(tableData[j]==null ) {
						continue;
					}
					else {
						if(tableData[i].getFullName().compareTo(tableData[j].getFullName())==1) {
							String temp=tableData[i].getFullName();
							tableData[i].setFullName(tableData[j].getFullName());
							tableData[j].setFullName(temp);
						}
					}
				}
			}
			}
		
	}

}
