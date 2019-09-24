import pbouda.gctester.ConsoleLogger.CustomLoggerFinder;
import java.lang.System.LoggerFinder;

module gctester {

    provides LoggerFinder with CustomLoggerFinder;
}