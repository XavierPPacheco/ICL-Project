class	VCell	implements	IValue	{
    IValue	v;

    VCell(IValue	v0)	{
        v	=	v0;
    }

    IValue	get(){
        return	v;
    }

    void	set(IValue	v0){
        v	=	v0;
    }
}