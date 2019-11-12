package Principal;

import java.util.Comparator;

public class IDComparator implements Comparator<Processo>{

	@Override
	public int compare(Processo o1, Processo o2) {
		return o1.getId().compareTo(o2.getId());
	}

}
