package os;
import java.util.Scanner;


public class jjj {
	private int Needed[][], Existing[], np, nr, safe[], Assigned[][], Waiting[], inServe[];
	double Utilization[];
	public int time = 0;
	int index = 0;// for safe array and unsafe
	boolean unsafeF = false;
	boolean workInT[] = new boolean[5];// flag array for resoureces
	int ser = 0;
	

	/// input method
	private void System_input() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number of Triness   : ");
		// check from number of students
		np = sc.nextInt();
		while (np > 100) {
			System.out.print("Enter again please : ");
			np = sc.nextInt();
		}

		nr = 5; // no. of resources
		Needed = new int[np][nr]; // initializing arrays
		Waiting = new int[np];// waiting for every student
		inServe = new int[np];
		Existing = new int[nr];// existing array
		safe = new int[np];// safe sequence
		Assigned = new int[np][nr];// allocated recourses

		System.out.println("Enter Existing matrix (number of instance for every resource) :");
		System.out.println("R1=pianos , R2=gutares , R3=violins , R4=trumpets , R5=drums .");
		for (int j = 0; j < nr; j++) {
			System.out.print("R" + (j + 1) + ":");
			Existing[j] = sc.nextInt();
			System.out.println();
		} // available matrix

		// need matrix
		System.out.println("Enter need matrix : ");
		for (int i = 0; i < nr; i++) {
			System.out.println("Enter the number of max need instances in R " + (i + 1) + ":");

			for (int j = 0; j < np; j++) {
				System.out.println("for Triness : " + (j + 1) + ":");
				Needed[j][i] = sc.nextInt();
			}
		}

		// assigned matrix
		for (int m = 0; m < nr; m++) {
			System.out.println("Enter (1) if the instances is assigned otherwise (0),in R" + (m + 1) + ":");
			for (int r = 0; r < np; r++) {
				System.out.print("for student " + (r + 1) + " :");
				Assigned[r][m] = sc.nextInt();
			}
		} 

		Utilization = RU();
	}/// end of input

	// safe or not
	public void isSafe() {
		System_input();

		boolean done[] = new boolean[np];
		for (int i = 0; i < np; i++)
			inServe[i] = -1;
		int count = 0;
		// initial values
		int inN = 0;
		int[] arrNe = new int[np];
		for (int i = 0; i < np; i++) {
			arrNe[i] = -1;
		}
		while (time < (15)) { // until all process allocated

			boolean allocated = false;
			System.out.println("At T" + (time));
			System.out.println("--------------------------");
			System.out.println("Need matrices :");
			printMop(Needed);
			System.out.println("--------------------------");

			System.out.println("Assigned matrices :");
			printMop(Assigned);
			for (int i = 0; i < np; i++) {

				if (!done[i] && check(i)) { // trying to allocate
int mn =0; 
					boolean serv = false;
					for (int p = 0; p < ser; p++) {
						if (i == inServe[p])
							serv = true;
					}

					if ((workInT[0] == true && (Needed[i][0] > 0) && !serv)
							|| (workInT[1] == true && (Needed[i][1] > 0) && !serv)
							|| (workInT[2] == true && (Needed[i][2] > 0) && !serv)
							|| (workInT[3] == true && (Needed[i][3] > 0) && !serv)
							|| (workInT[4] == true && (Needed[i][4] > 0) && !serv)) {
						Waiting[i] = time + 1;
						continue;
					}

					for (int p = 0; p < nr; p++) {
						if (Needed[i][p] > 0) {
							if(Assigned[mn][p]==Assigned[i][p]) {
					    		Assigned[i][p]=1;
					    		workInT[p] = true;
					    		mn=i;
					    	   break;}
					
						}
					}

					Needed = calc_need(i);
					if (!serv)
						inServe[ser++] = i;

					for (int k = 0; k < nr; k++) {
						Existing[k] = Existing[k] - Assigned[i][k];

					}
					boolean flagd = true;
					if (Done(i, Needed)) {
						allocated = done[i] = true;
						count++;
						safe[index] = i;
						index++;

						for (int k = 0; k < ser; k++) {
							if (inServe[k] == i) {
								inServe[k] = -1;
								for (int r = k; r < inServe.length - 1; r++)
									inServe[r] = inServe[r + 1];
								ser--;
								break;
							}
						}
						arrNe[inN++] = i;

					}
				}
			}
			for (int o = 0; o < inN; o++) {
				for (int k = 0; k < nr; k++) {
					if (Assigned[arrNe[o]][k] == 1) {
						workInT[k] = false;
					}
				}
				for (int k = 0; k < nr; k++)
					Assigned[arrNe[o]][k] = 0;
			}
			for (int i = 0; i < np; i++)
				arrNe[i] = -1;
			inN = 0;
			System.out.println(count);
			time++;
			if (time == 15)
				break;
		} // end while 14

		if (isAllDone()) {
			System.out.println("Safe Sequence: ");
			for (int i = 0; i < np; i++) {
				System.out.print(safe[i] + " ");
			}
			System.out.println();
			int totalW = 0;
			System.out.println("Waiting Time: ");
			for (int i = 0; i < np; i++) {
				System.out.println("Triness " + (i) + " : " + Waiting[i]);
				totalW += Waiting[i];
			}
			System.out.println("Resource Utilization: ");
			for (int i = 0; i < nr; i++)
				System.out.printf("Resource %d : %.2f %%\n", i, (Utilization[i] * 100));
			System.out.printf("Average wait: %.2f ", (totalW / (double) np));
		} else
			System.out.print("unsafe");
	}

	public static void main(String[] args) {
		new jjj().isSafe();
	}

	void printMop(int m[][]) {
		for (int i = 0; i < m.length; i++) {
			System.out.println("Triness: " + (i + 1));
			for (int j = 0; j < m[0].length; j++) {
				System.out.print(m[i][j]);
			}
			System.out.printl				if (Needed[j][i] != 0)
					return false;
			}
		return true;
	}

	// calculate Utilization
	public double[] RU() {
		double total = 0;
		double Utilization[] = new double[nr];
		for (int i = 0; i < nr; i++) {
			total = 0;
			for (int c = 0; c < np; c++) {
				total += Needed[c][i];
			}
			if (Existing[i] != 0)
				Utilization[i] = total / (double) 14;
			else
				Utilization[i] = 0;
		}
		return Utilization;
	}

	/// done method
	public boolean Done(int i, int need[][]) {
		for (int n = 0; n < nr; n++) {
			if (need[i][n] != 0)
				return false;
		}
		return true;
	}

	private int[][] calc_need(int i) {

		for (int j = 0; j < nr; j++) // calculating need matrix
			if (Needed[i][j] != 0)
				Needed[i][j] = Needed[i][j] - Assigned[i][j];

		return Needed;
	}

	private boolean check(int i) {
		// checking if all resources for ith process can be allocated
		for (int j = 0; j < nr; j++)
			if (Existing[j] < Needed[i][j] || (14 - time < Needed[i][j]))
				return false;
		return true;
	}
}
