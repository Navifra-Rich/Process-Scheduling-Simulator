package com.func;

import com.pro.Process;

public class SPN extends Scheduling {
	public SPN(int processorNum) {
		super(processorNum);
	}

	@Override
	public Process[] doScheduling() {
		int index = 0;
		int spn_idx = 0;// spn 스케줄링에서 우선순위를 정하기 위한 인덱스
		Process[] rePro = new Process[15];
		printProcessInfo(); // 프로세스 정보 출력. 디버깅용
		for (int time = 0; !isEnd(); time++) {
			setWaitQ(time); // 매 시간단위마다 도착한 프로세스를 대기큐에 넣어줍니다.

			// System.out.println("time : "+time+"Index : "+index+"size : "+waitQ.size());
			for (int i = 0; i < processorNum; i++) { // 프로세서의 개수만큼 반복
				if (processor[i] != null) {
					runStatus[i][time] = processor[i].getID();
				} else
					runStatus[i][time] = -1;
				if (checkRemain(time, i)) { // 프로세스가 끝났는지 검사합니다.
					rePro[index++] = processor[i];
					processor[i] = null;
				}
				if (processor[i] == null) { // 프로세서가 비어있을경우
					if (!waitQ.isEmpty()) { // 그리고 대기큐에 프로세스가 있는경우
						// time이 지날때마다 burst_time이 가장 적은 프로세스의 인덱스를 찾아서 설정해준다.
						int min = Integer.MAX_VALUE;
						for (int ii = 0; ii < waitQ.size(); ii++) {
							if (waitQ.get(ii).getBurTime() < min) {
								min = waitQ.get(ii).getBurTime();
								spn_idx = ii;
							}
						}
						processor[i] = waitQ.get(spn_idx); // 대기큐에서 bur_time이 가장 적은 프로세스를 가져옵니다.
						waitQ.remove(spn_idx);// 가져온 큐를 삭제합니다.
					}
				} else
					processor[i].reduRemainTime(); // 프로세서가 사용되고있으면 remain time을 감소시킵니다.
			}
		}
		return rePro;
	}
}
