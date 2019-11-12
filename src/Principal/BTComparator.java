package Principal;

import java.util.Comparator;

public class BTComparator implements Comparator<Processo> {

	@Override
	public int compare(Processo o1, Processo o2) {
		return o1.getBt().compareTo(o2.getBt());
	}

}
