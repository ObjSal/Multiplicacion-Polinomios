public interface STATE
{
	public final int LOADING =-1;
	public final int MENU=LOADING+1;//0
	public final int MENU_ACERCA_DE=MENU+1;//1
	public final int MENU_AYUDA=MENU_ACERCA_DE+1;//2
	public final int IN_CLASS=MENU_AYUDA+1;//3
	public final int SALIR_APP=IN_CLASS+1;//4
	
	//selected items del MM
	public final int NUEVO=SALIR_APP+1;//5
	public final int CONTINUAR=NUEVO+1;//6
	public final int AYUDA=CONTINUAR+1;//7
	public final int ACERCA_DE=AYUDA+1;//8
	public final int SALIR=ACERCA_DE+1;//9
	
	public final int CLASS_INPUT1=SALIR+1;//10
	public final int CLASS_INPUT2=CLASS_INPUT1+1;//11
	public final int CLASS_INPUT_STEP=CLASS_INPUT2+1;//12
	public final int CLASS_FIRST_STEP=CLASS_INPUT_STEP+1;//13
	public final int CLASS_SECOND_STEP=CLASS_FIRST_STEP+1;//14
	public final int CLASS_THIRD_STEP=CLASS_SECOND_STEP+1;//15
	public final int CLASS_FOURTH_STEP=CLASS_THIRD_STEP+1;//16
	public final int CLASS_FIFTH_STEP=CLASS_FOURTH_STEP+1;//17
}