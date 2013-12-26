/**
 * Copyright 2013 Lukas Nalezenec
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package parquet.proto;

import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import org.apache.hadoop.mapreduce.Job;
import parquet.hadoop.ParquetOutputFormat;
import parquet.hadoop.util.ContextUtil;

/**
 * A Hadoop {@link org.apache.hadoop.mapreduce.OutputFormat} for Protobuffer Parquet files.
 * <p/>
 * Usage:
 * <p/>
 * <pre>
 * {@code
 * final Job job = new Job(conf, "Parquet writing job");
 * job.setOutputFormatClass(ProtoParquetOutputFormat.class);
 * ProtoParquetOutputFormat.setOutputPath(job, parquetPath);
 * ProtoParquetOutputFormat.setProtobufferClass(job, YourProtobuffer.class);
 * }
 * </pre>
 *
 * @author Lukas Nalezenec
 */
public class ProtoParquetOutputFormat<T extends MessageOrBuilder> extends ParquetOutputFormat<T> {

  public static void setProtobufferClass(Job job, Class<? extends Message> protoClass) {
    ProtoWriteSupport.setSchema(ContextUtil.getConfiguration(job), protoClass);
  }

  public ProtoParquetOutputFormat(Class<? extends Message> msg) {
    super(new ProtoWriteSupport(msg));
  }

  public ProtoParquetOutputFormat() {
    super(new ProtoWriteSupport());
  }

}
