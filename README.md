Swipe 공부를 위한 데모 프로젝트입니다.

UI 설명:
Box 메서드를 사용해 선과 선 왼쪽, 가운데, 오른쪽에 각 동그라미 하나를 그립니다.
그리고 움직이는 네모 모양 박스를 하나 그립니다.

1. swipeable 상태와 앵커를 설정합니다. (rememberSwipeableState, mapOf(...) 내용)
2. Box의 Modifier중 swipeable 메서드를 정의(state: 상태, achors: 엥커(영역 시작, 중간, 끝 지점), 
  thresholds: 임계점(어느정도 움직였을 때 다음 엥커로 자연스레 이동하게 할지 지정), orientation: 수평, 수직 설정)

swipeable 메서드를 설정한 Box 안에 UI를 그려서 swipeable에 따라 움직이도록 합니다.
