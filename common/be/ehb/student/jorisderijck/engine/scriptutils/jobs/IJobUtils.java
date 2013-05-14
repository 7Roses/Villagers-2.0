package be.ehb.student.jorisderijck.engine.scriptutils.jobs;

/**
 * interface for easier creation of job objects within the js codes.
 * (no need to know the packaging or class names, only a certain facade)
 * */
public interface IJobUtils {

    public static final AtomicalJob NULLJOB = new AtomicalJob("null");
    
    public AtomicalJob createAtomicJob(String jobname);
    public AtomicalJob createAtomicJobWithProperties(String jobname,JobProperty ... properties);
    
    public ComplexJob createComplexJob(String jobname);
    public ComplexJob createComplexJobWithJobs(String jobname,AbstractJob ... jobs);
}
