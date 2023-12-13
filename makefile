mvp:
	java -jar generator/generator.jar -k irregular -h 1080 -w 1920 -p 1000 -s 20 -o img/mvp.mesh; \
	java -jar island/island.jar -i img/irregular.mesh -o img/transition.mesh -s circle -m; \
	java -jar visualizer/visualizer.jar -i img/transition.mesh -o img/mvp.svg; \

circleVolcanoLARS:
	java -jar generator/generator.jar -k irregular -h 1080 -w 1920 -p 1000 -s 20 -o img/irregular.mesh; \
	java -jar island/island.jar -i img/irregular.mesh -o img/transition.mesh -s circle -a volcano -l 12 -aq 13 -r 4 -soil dry -b hawaii -c 2; \
	java -jar visualizer/visualizer.jar -i img/transition.mesh -o img/irregular.svg; \

starVolcanoLARS:
	java -jar generator/generator.jar -k irregular -h 1080 -w 1920 -p 1000 -s 20 -o img/irregular.mesh; \
	java -jar island/island.jar -i img/irregular.mesh -o img/transition.mesh -s star -a volcano -l 2 -aq 10 -r 3 -soil wet -b hawaii -c 2; \
	java -jar visualizer/visualizer.jar -i img/transition.mesh -o img/irregular.svg; \

circleCraterLARS:
	java -jar generator/generator.jar -k irregular -h 1080 -w 1920 -p 1000 -s 20 -o img/irregular.mesh; \
	java -jar island/island.jar -i img/irregular.mesh -o img/transition.mesh -s circle -a crater -l 6 -aq 3 -r 6 -soil wet -b peru -c 2; \
	java -jar visualizer/visualizer.jar -i img/transition.mesh -o img/irregular.svg; \

starCraterLARS:
	java -jar generator/generator.jar -k irregular -h 1080 -w 1920 -p 1000 -s 20 -o img/irregular.mesh; \
	java -jar island/island.jar -i img/irregular.mesh -o img/transition.mesh -s star -a crater -l 0 -aq 0 -r 0 -soil wet -b hawaii -c 2; \
	java -jar visualizer/visualizer.jar -i img/transition.mesh -o img/irregular.svg; \

.PHONY: run